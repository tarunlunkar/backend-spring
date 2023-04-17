package com.example.miniproject.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity(name="inventory_details")

public class InventoryEntity {
    @Id
    private Integer product_id;

    @Size(min=2)
    @JsonProperty("prod_name")
    private String product_name;
    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private Integer price;

}
