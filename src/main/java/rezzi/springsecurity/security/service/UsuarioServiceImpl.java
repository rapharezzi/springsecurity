package rezzi.springsecurity.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rezzi.springsecurity.security.entity.Usuario;
import rezzi.springsecurity.security.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscarPorUsername(String username) {
		return Optional.ofNullable(this.usuarioRepository.findByUsername(username));
	}
}
