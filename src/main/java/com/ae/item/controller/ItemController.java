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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Value("${config.text}")
    private String text;

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

    @GetMapping("/config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        logger.info(String.format("TEXT: %s", text));

        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        map.put("port", port);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
