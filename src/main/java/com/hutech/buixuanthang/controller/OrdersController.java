package com.hutech.buixuanthang.controller;

import com.hutech.buixuanthang.model.CartItem;
import com.hutech.buixuanthang.model.Order;
import com.hutech.buixuanthang.service.CartService;
import com.hutech.buixuanthang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("listorder")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders/listorder";
    }

    @GetMapping("/details")
    public String orderDetails(@RequestParam("id") Long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "orders/order-details";
    }

    @GetMapping("/confirm")
    public String confirmOrder(@RequestParam("id") Long id) {
        orderService.confirmOrder(id);
        return "redirect:/orders/listorder";
    }

    @GetMapping("/cancel-confirm")
    public String cancelConfirmOrder(@RequestParam("id") Long id) {
        orderService.confirmCancelOrder(id);
        return "redirect:/orders/listorder";
    }

    @GetMapping("/complete")
    public String completeOrder(@RequestParam("id") Long id) {
        orderService.completeOrder(id);
        return "redirect:/orders/listorder";
    }
}
