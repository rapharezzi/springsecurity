package rezzi.springsecurity.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import rezzi.springsecurity.security.entity.Usuario;
import rezzi.springsecurity.security.enums.Perfil;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/** Converte e gera um JwtUser com base nos dados de um funcionário.
	 * 
	 * @param funcionario
	 * @return JwtUser */
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getUsername(), usuario.getPassword(),
				mapToGrantedAuthorities(usuario.getPerfil()));
	}

	/** Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority> */
	private static List<GrantedAuthority> mapToGrantedAuthorities(Perfil perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}
