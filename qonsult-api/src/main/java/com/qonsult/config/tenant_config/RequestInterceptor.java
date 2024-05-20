//package com.qonsult.config.tenant_config;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
//@Component
//public class RequestInterceptor extends OncePerRequestFilter {
//
//	@Autowired
//	private ApplicationContext context;
//	private static Logger log = LoggerFactory.getLogger(RequestInterceptor.class);
//
//
//
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//		String requestURI = request.getRequestURI();
//		String schema="";
//		if(requestURI.contains("api/public/auth")){
//			String username = request.getHeader("username");
//			TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
//			schema = tenantDataSource.getUsernameSchemaMap().get(username);
//		}
//		else if(requestURI.contains("api/public/")){
//			schema = request.getHeader("schema");
//		}
//		else if(request.getHeader(AUTHORIZATION)!=null){
//		String authorizationHeader = request.getHeader(AUTHORIZATION);
//		String token = authorizationHeader.substring("Bearer ".length());
//		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//		JWTVerifier verifier = JWT.require(algorithm).build();
//		DecodedJWT decodedJWT = verifier.verify(token);
//		schema = decodedJWT.getClaim("schema").asString();
//
//	}
//		TenantContext.setCurrentTenant(schema);
//		filterChain.doFilter(request, response);
//	}
//}
