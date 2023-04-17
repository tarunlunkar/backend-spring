package com.example.miniproject.Repository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderCreation {
    @Autowired
    private OrderEntity oe;
    private static int order_id=0;
    public OrderEntity save(String order_name, int num_of_orders) {
        oe.setOrder_id(++order_id);
        oe.setOrder_type(order_name);
        oe.setNumber_of_products(num_of_orders);
        return oe;
    }
}
