package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.CartItem;
import com.hutech.buixuanthang.model.Order;
import com.hutech.buixuanthang.model.OrderDetail;
import com.hutech.buixuanthang.service.CartService;
import com.hutech.buixuanthang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @GetMapping("/checkout")
    public String checkout() {
        return "/order/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(@RequestParam("customerName") String customerName,
                              @RequestParam("phone") String phone,
                              @RequestParam("email") String email,
                              @RequestParam("address") String address,
                              @RequestParam("note") String note,
                              @RequestParam("paymentMethod") String paymentMethod) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Redirect if cart is empty
        }
        orderService.createOrder(customerName, phone, email, address, note, paymentMethod, cartItems);
        return "redirect:/order/confirmation";
    }

    @GetMapping("")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();

        model.addAttribute("orders", orders);
        return "order/index";
    }


//    @GetMapping("/calculateTotal")
//    public String calculateTotal(Model model) {
//        List<Order> orders = orderService.getAllOrders();
//        for (Order order : orders) {
//            double totalAmount = order.getOrderDetails().stream()
//                    .mapToDouble(detail -> detail.getQuantity() * detail.getProduct().getPrice())
//                    .sum();
//            order.setTotalAmount(totalAmount);
//        }
//        model.addAttribute("orders", orders);
//        return "order/index";
//    }

    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "order/order-confirmation";
    }

    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/order";
    }

    @PostMapping("/received")
    public String receivedOrder(@RequestParam("orderId") Long orderId) {
        orderService.markOrderAsReceived(orderId);
        return "redirect:/order";
    }
}