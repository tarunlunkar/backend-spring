package com.example.miniproject.Service;

import com.example.miniproject.Service.Exceptions.OutofStockException;
import com.example.miniproject.Repository.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.*;


@Data
@Service
public class service {
    private InventoryRepository repo;
    private OrderCreation cr;
    private OrderRepository orepo;

    private List<InventoryEntity> ListOfProducts;


    public service(InventoryRepository repo, OrderCreation cr, OrderRepository orepo){
        this.repo=repo;
        this.cr=cr;
        this.orepo=orepo;
    }
    Map<Integer,Integer> currentQuantity=new HashMap<>();
    int purchaseCount=0;
    int salesCount=0;

    public List<InventoryEntity> getProductDetails(){
        return repo.findAll();
    }

    public List<OrderEntity> getOrderDetails(){
        return orepo.findAll();
    }

    public ResponseEntity<Object> purchaseOrder(List<InventoryEntity> inventory){
        for(InventoryEntity product:inventory){
            currentQuantity.put(product.getProduct_id(),product.getQuantity());
        }
        repo.saveAll(inventory);
        purchaseCount++;
        OrderEntity order=cr.save("Purchase Order",inventory.size());
        orepo.save(order);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrder_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public Optional<InventoryEntity> particularProduct(int id){
        Optional<InventoryEntity> product=repo.findById(id);
        return product;
    }
    public Optional<OrderEntity> particularOrder(int id){
        Optional<OrderEntity> order=orepo.findById(id);
        return order;
    }

    public ResponseEntity<Object> salesOrder(List<InventoryEntity> inventory){
        for(InventoryEntity product:inventory) {
            if ((product.getQuantity() > currentQuantity.get(product.getProduct_id())) || (currentQuantity.get(product.getProduct_id()) <= 0)) {
                throw new OutofStockException("Out of Stock!!");
            } else {
                int rem = currentQuantity.get(product.getProduct_id()) - product.getQuantity();
                product.setQuantity(rem);

                currentQuantity.put(product.getProduct_id(), rem);
                repo.save(product);

            }
        }
        OrderEntity order = cr.save("Sales Order", inventory.size());
        orepo.save(order);
        salesCount++;
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getOrder_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public String transactions(){
        return "Total Transaction:"+Integer.toString((int)orepo.count())+
                "\nPurchase Transaction:"+Integer.toString(purchaseCount)+
                "\nSales Transaction:"+Integer.toString(salesCount);
    }
}
