package in.myproject.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String email;
    private String token;
}
