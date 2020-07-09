package com.ae.item.service;

import com.ae.item.model.Item;
import com.ae.item.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restClient;

    @Override
    public List<Item> findAllItems() {
        List<Product> products = Arrays.asList(
                restClient
                        .getForObject(
                                "http://localhost:8001/product",
                                Product[].class ));
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findItemById(Long id, Integer count) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Product product = restClient.getForObject("http://localhost:8001/product/{id}", Product.class, pathVariables);

        return new Item(product, count);
    }
}
