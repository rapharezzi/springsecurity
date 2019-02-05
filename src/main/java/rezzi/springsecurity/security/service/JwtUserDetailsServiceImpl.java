package rezzi.springsecurity.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rezzi.springsecurity.security.JwtUserFactory;
import rezzi.springsecurity.security.entity.Usuario;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> funcionario = usuarioService.buscarPorUsername(username);
		if (funcionario.isPresent())
			return JwtUserFactory.create(funcionario.get());
		
		throw new UsernameNotFoundException("Usuário não encontrado.");
	}

}
