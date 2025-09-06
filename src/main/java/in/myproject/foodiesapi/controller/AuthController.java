package in.myproject.foodiesapi.controller;

import in.myproject.foodiesapi.io.AuthenticationRequest;
import in.myproject.foodiesapi.io.AuthenticationResponse;
import in.myproject.foodiesapi.service.AppUserDetailsService;
import in.myproject.foodiesapi.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
     final UserDetails userDetails=   userDetailsService.loadUserByUsername(request.getEmail());
    final String jwtToken = jwtUtil.generateToken(userDetails);
    return new AuthenticationResponse(request.getEmail(),jwtToken);
    }
}
