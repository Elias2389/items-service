package com.ae.item.controller;

import com.ae.item.model.Item;
import com.ae.item.model.Product;
import com.ae.item.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Properties;

@RestController
public class ItemController {

    private final ItemService service;

    public ItemController(@Qualifier("ItemServiceFeing") ItemService service) {
        this.service = service;
    }

    @GetMapping("/item")
    public List<Item> findItems() {
        return service.findAllItems();
    }

    @HystrixCommand(fallbackMethod = "errorItemMethod")
    @GetMapping("/item/{id}/count/{count}")
    public Item findItems(@PathVariable final Long id, @PathVariable final Integer count) {
        return service.findItemById(id, count);
    }

    public Item errorItemMethod(final Long id, final Integer count) {
        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("test Product");
        product.setPrice(500D);

        item.setCount(count);
        item.setProduct(product);


        return item;
    }
}
