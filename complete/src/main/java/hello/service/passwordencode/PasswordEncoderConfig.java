package hello.service.passwordencode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mindrot.jbcrypt.BCrypt;

@Configuration
public class PasswordEncoderConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
            	return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
            	return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        };
    }
}
