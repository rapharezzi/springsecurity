package rezzi.springsecurity.security.dto;

import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {

	private String username;
	private String password;

	public JwtAuthenticationDto() {
	}

	@NotEmpty(message = "Login não pode ser vazio.")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getPassword() {
		if (password == null || password.isEmpty())
			return "";
		return password; 
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [email=" + username + ", senha=" + password + "]";
	}

}
