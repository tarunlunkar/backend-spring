package com.example.miniproject.Controller;

import com.example.miniproject.Repository.InventoryEntity;
import com.example.miniproject.Repository.OrderEntity;
import com.example.miniproject.Service.service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class Resource {

    @Autowired
    private service ser;
    public Resource(service ser) {
       this.ser = ser;
    }

    @GetMapping("/inventory_details")
    public List<InventoryEntity> retrieveInventory(){
        return ser.getProductDetails();
    }
    @GetMapping("/order_details")
    public List<OrderEntity> retrieveOrder(){
        return ser.getOrderDetails();
    }
    @GetMapping("/inventory_details/product/{id}")
    public Optional<InventoryEntity> retrieveParticularProduct(@PathVariable int id){
        return ser.particularProduct(id);
    }
    @GetMapping("/inventory_details/order/{id}")
    public Optional<OrderEntity> retrieveParticularOrder(@PathVariable int id){
        return ser.particularOrder(id);
    }
    @PostMapping("/order")
    public ResponseEntity<Object> createPurchaseOrder(@Valid @RequestBody List<InventoryEntity> inventory){
        return ser.purchaseOrder(inventory);
    }
    @PutMapping("/order")
    public ResponseEntity<Object> createSaleOrder(@Valid @RequestBody List<InventoryEntity> inventory){
        return ser.salesOrder(inventory);
    }
    @GetMapping("/inventory_details/total_transactions")
    public String totalTransaction(){
       return ser.transactions();
    }
}
