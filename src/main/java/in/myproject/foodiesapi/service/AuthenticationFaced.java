package in.myproject.foodiesapi.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFaced {
    Authentication getAuthentication();
}
