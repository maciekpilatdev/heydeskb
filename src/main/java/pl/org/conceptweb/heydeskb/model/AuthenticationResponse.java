package pl.org.conceptweb.heydeskb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
private final String jwt;
private final String jwtExpirationTime;
private final String userName;
private final String message;    
}
