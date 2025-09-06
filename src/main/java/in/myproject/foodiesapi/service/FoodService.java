package in.myproject.foodiesapi.service;

import in.myproject.foodiesapi.io.FoodRequest;
import in.myproject.foodiesapi.io.FoodResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodService {
   String uploadFile(MultipartFile file);
FoodResponse addFood(FoodRequest request , MultipartFile file);

List<FoodResponse> readFood();

FoodResponse readFood(String id);

boolean deleteFile(String filename);

 void deleteFood(String id);
}
