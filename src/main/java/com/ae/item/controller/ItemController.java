package com.ae.item.controller;

import com.ae.item.model.Item;
import com.ae.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("ItemServiceFeing")
    private ItemService service;


    @GetMapping("/item")
    public List<Item> findItems() {
        return service.findAllItems();
    }

    @GetMapping("/item/{id}/count/{count}")
    public Item findItems(@PathVariable final Long id, @PathVariable final Integer count) {
        return service.findItemById(id, count);
    }
}
