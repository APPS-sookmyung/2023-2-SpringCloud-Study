package com.example.apigatewayservice.filter;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    Environment env; //application.yml에서 특정한 정보 가져오기 위함

    public AuthorizationHeaderFilter(Environment env){
        super(Config.class); //부모에 Configuration 정보를 전달 -> 하지 않으면 ClassCastException 발생
        this.env=env;
    }
    public static class Config{

    }

    //과정: login -> token 반환받음 -> 클라이언트에서 users 요청(with token)
    //-> header(include token) 서버측에서 token이 맞는지 확인
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            ServerHttpRequest request=exchange.getRequest(); //요청 정보
            //사용자가 헤더에 로그인을 했을때 받았던 토큰을 전달해주는 코드 작성
            //사용자가 전달한 헤더에 authorization 정보가 포함되었는지 확인
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange,"no authorization header", HttpStatus.UNAUTHORIZED);
            }
            //헤더에 authorization 정보가 있으면 가져옴
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            //token을 가져와서 Bearer을 빈 문자열("")로 변경하고 나머지를 token으로 인식
            String jwt= authorizationHeader.replace("Bearer","");

            //만약 유효하지 않은 token이라면 에러 메시지 출력 
            if (!isJwtValid(jwt)){
                return onError(exchange,"JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange); //통과라는 메시지 return
        };
    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue=true;

        String subject=null;
        try{
            subject= Jwts.parser().setSigningKey(env.getProperty("token.secret")) //token.secret을 가지고 복호화
                    .parseClaimsJws(jwt).getBody()//문자형 token으로 parsing
                    .getSubject(); //그 안에서 subject만 추출
        }catch(Exception ex){
            returnValue=false;
        }

        //정상적인 subject인가
        if (subject ==null || subject.isEmpty()){
            returnValue=false;
        }
        return returnValue;
    }

    // mono, flux 는 spring WebFlux(MVC가 아님! spring 5.0에서 추가됨)에서 나온 개념
    // 클라이언트 요청이 왔을때 반환시켜주는 데이터 타입
    //단일값이면 mono, 여러 값이면 flux 사용
    //webflux에서는 servlet 개념을 사용하지 않음
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response= exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        return response.setComplete();
    }
}
