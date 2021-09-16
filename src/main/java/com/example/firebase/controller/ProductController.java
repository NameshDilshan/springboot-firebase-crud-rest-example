package com.example.firebase.controller;

import com.example.firebase.entity.Product;
import com.example.firebase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public String saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.saveProduct(product);
    }

    @GetMapping("/products/{name}")
    public Product getProductDetailsByName(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productService.getProductDetailsByName(name);
    }

    @GetMapping("/products")
    public List<Product> getAllProductDetails() throws ExecutionException, InterruptedException {
        return productService.getProductDetails();
    }

    @PutMapping("/products")
    public String updateProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{name}")
    public String updateProduct(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productService.deleteProduct(name);
    }




}
