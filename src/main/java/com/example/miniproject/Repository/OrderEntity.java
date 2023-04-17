package com.example.miniproject.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Entity(name="order_details")
@Data
public class OrderEntity {
    @Id
    private Integer order_id;

    private String order_type;

    private Integer number_of_products;
}
