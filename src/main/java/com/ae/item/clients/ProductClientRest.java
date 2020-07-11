package com.ae.item.clients;

import com.ae.item.model.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClientRest {

    /**
     * @return List of products
     */
    @GetMapping("/product")
    public List<Product> getProducts();

    /**
     * @param id of product
     * @return product by id
     */
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable final Long id);

    /**
     * @param product to create
     * @return product created
     */
    @PostMapping("/product")
    public Product createProduct(@RequestBody final Product product);

    /**
     * @param product to update
     * @return product updated
     */
    @PutMapping("/product")
    public Product updateProduct(@RequestBody final Product product);

    /**
     * @param id of product to delete
     */
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") final Long id);

}
