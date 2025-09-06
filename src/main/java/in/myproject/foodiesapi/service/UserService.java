package in.myproject.foodiesapi.service;

import in.myproject.foodiesapi.io.UserRequest;
import in.myproject.foodiesapi.io.UserResponse;

public interface UserService {
 UserResponse registerUser(UserRequest request);

 String findByUserId();

}
