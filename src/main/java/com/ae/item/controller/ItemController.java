package com.ae.item.controller;

import com.ae.item.model.Item;
import com.ae.item.model.Product;
import com.ae.item.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RefreshScope
@RestController
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(@Qualifier("ItemServiceFeing") ItemService service) {
        this.service = service;
    }

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Value("${config.text}")
    private String text;

    @GetMapping("/item")
    public ResponseEntity<List<Item>> findItems() {
        List<Item> items = service.findAllItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "errorItemMethod")
    @GetMapping("/item/{id}/count/{count}")
    public ResponseEntity<Item> findItems(@PathVariable final Long id, @PathVariable final Integer count) {
        Item item = service.findItemById(id, count);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    public ResponseEntity<Item>  errorItemMethod(final Long id, final Integer count) {
        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("test Product");
        product.setPrice(500D);

        item.setCount(count);
        item.setProduct(product);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        logger.info(String.format("TEXT: %s", text));

        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        map.put("port", port);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Item> createProduct(@RequestBody final Product product) {
        Item item = service.createProduct(product);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/item")
    public ResponseEntity<Item> updateProduct(@RequestBody final Product product) {
        Item item = service.updateProduct(product);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") final Long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
