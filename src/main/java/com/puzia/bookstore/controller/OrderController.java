package com.puzia.bookstore.controller;

import com.puzia.bookstore.service.OrderService;
import com.puzia.bookstore.service.model.NewOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @PostMapping
    public EntityCreatedResponse createNewOrder(@RequestParam("userId") int userId, @RequestBody NewOrderDetails request) {
        return EntityCreatedResponse.fromId(orderService.addNewOrder(userId, request));
    }

}
