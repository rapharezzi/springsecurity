package rezzi.springsecurity.security.service;

import java.util.Optional;

import rezzi.springsecurity.security.entity.Usuario;

public interface UsuarioService {

	/**
	 * Busca e retorna um usuário dado um email.
	 * 
	 * @param email
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorUsername(String username);

}
