package hello.service.passwordencode;

public interface PasswordEncoder {

	String encode(CharSequence rawPassword);
	
    boolean matches(CharSequence rawPassword, String encodedPassword);
    
}
