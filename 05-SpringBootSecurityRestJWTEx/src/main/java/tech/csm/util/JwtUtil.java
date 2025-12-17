package tech.csm.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	@Value("${app.secret}")
	private String secrect;

	public String generateToken(String username) {
		return null;
	}

}
