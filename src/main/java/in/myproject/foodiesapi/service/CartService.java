package in.myproject.foodiesapi.service;

import in.myproject.foodiesapi.io.CartRequest;
import in.myproject.foodiesapi.io.CartResponse;

public interface CartService {

   CartResponse addToCart(CartRequest request);

   CartResponse getCart();

   void clearCart();

 CartResponse  removeFromCart(CartRequest cartRequest);
}
