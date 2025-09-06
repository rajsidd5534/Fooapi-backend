package in.myproject.foodiesapi.service;

import in.myproject.foodiesapi.entity.UserEntity;
import in.myproject.foodiesapi.io.UserRequest;
import in.myproject.foodiesapi.io.UserResponse;
import in.myproject.foodiesapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService  {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFaced authenticationFaced;
    @Override
    public UserResponse registerUser(UserRequest request) {
 UserEntity newUser = convertToEntity(request);
 newUser = userRepository.save(newUser);
 return  convertToResponse(newUser);
    }

    @Override
    public String findByUserId() {
     String loggedInUserEmail =   authenticationFaced.getAuthentication().getName();
  UserEntity loggedInUser =  userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UsernameNotFoundException("User Not found "));
  return loggedInUser.getId();
    }

    private UserEntity convertToEntity(UserRequest request){
return UserEntity.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .build();
    }

    private UserResponse convertToResponse(UserEntity registeredUser){
      return   UserResponse.builder()
                .id(registeredUser.getId())
                .name(registeredUser.getName())
                .email(registeredUser.getEmail())
                .build();
    }
}
