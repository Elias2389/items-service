package com.ae.item.clients;

import com.ae.item.model.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
    public interface ProductClientRest {

    @GetMapping("/product")
    public List<Product> getProducts();

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable final Long id );

}
