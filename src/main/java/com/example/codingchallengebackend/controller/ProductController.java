package com.example.codingchallengebackend.controller;

import com.example.codingchallengebackend.exception.ResourceNotFoundException;
import com.example.codingchallengebackend.model.Category;
import com.example.codingchallengebackend.model.Product;
import com.example.codingchallengebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//instead of creating endpoint manually we can also use rest repositories and expose default endpoint
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product not found for this id :: " + id));
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable(value = "id") int id,
                                 @RequestBody Product productDetails) throws ResourceNotFoundException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + id));
        product.setOriginalPrice(productDetails.getOriginalPrice());
        product.setEuroPrice(productDetails.getEuroPrice());
        product.setOriginalCurrency(productDetails.getOriginalCurrency());
        product.setTitle(productDetails.getTitle());
        return productRepository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id ) {
        productRepository.deleteById(id);
    }

    @GetMapping("/products/{id}/full_path")
    public String getProductFullPath(@PathVariable int id) throws ResourceNotFoundException {
         Product product = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product not found for this id :: " + id));
         String path = "";
         Category category=product.getCategory();
         while (category!=null){
             path = path +" / "+ category.getTitle();
             category = category.getParent();
         }
         return path;
    }

    @GetMapping("/products/category/{id}")
    public List<Product> getProductsByCategoryId(@PathVariable int id) {
        return productRepository.findAllProductsByCategoryId(id);
    }
}
