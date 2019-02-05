package rezzi.springsecurity.security.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import rezzi.springsecurity.response.Response;
import rezzi.springsecurity.security.dto.TokenDto;
import rezzi.springsecurity.security.enums.HeaderTypeAuth;
import rezzi.springsecurity.security.util.JwtTokenUtil;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Response<TokenDto> responseDto = new Response<TokenDto>();
		Optional<String> token = Optional.ofNullable(request.getHeader(HeaderTypeAuth.TOKEN_HEADER.getValue()));

		if (token.isPresent() && token.get().startsWith(HeaderTypeAuth.BEARER_PREFIX.getValue()))
			token = Optional.of(token.get().substring(7));		

		if (!token.isPresent())
			responseDto.getErrors().add("Token não informado.");
		else if (!jwtTokenUtil.tokenValido(token.get()))
			responseDto.getErrors().add("Token inválido ou expirado.");
		return super.preHandle(request, response, handler);
	}

}
