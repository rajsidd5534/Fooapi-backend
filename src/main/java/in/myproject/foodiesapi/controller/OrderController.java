package in.myproject.foodiesapi.controller;

import com.razorpay.RazorpayException;
import in.myproject.foodiesapi.io.OrderRequest;
import in.myproject.foodiesapi.io.OrderResponse;
import in.myproject.foodiesapi.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "https://storied-duckanoo-76aa1f.netlify.app")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
@PostMapping("/create")
@ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) throws RazorpayException {
        OrderResponse response = orderService.createOrderWithPayment(request);
        return response;
    }
    @PostMapping("/verify")
    public void verifyPayment(@RequestBody Map<String , String> paymentData){
    orderService.verifyPayment(paymentData , "Paid");
    }

    @GetMapping
    public List<OrderResponse> getOrders(){
   return orderService.getUSerOrders();
    }
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String orderId){
    orderService.removeOrder(orderId);
    }
    //admin panel
    @GetMapping("/all")
    public List<OrderResponse> getOrdersOfAllUsers()  {
    return orderService.getOrdersOfAllUsers();
    }

    //admin panel
    @PatchMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId , @RequestParam String status){
    orderService.updateOrderStatus(orderId , status);
    }
}
