package rezzi.springsecurity.security.enums;

public enum HeaderTypeAuth {
	TOKEN_HEADER("Authorization"), BEARER_PREFIX("Bearer");
	
	private String value;
	
	HeaderTypeAuth(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
