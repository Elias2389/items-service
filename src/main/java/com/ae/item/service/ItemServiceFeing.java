package com.ae.item.service;

import com.ae.item.clients.ProductClientRest;
import com.ae.item.model.Item;
import com.ae.item.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("ItemServiceFeing")
public class ItemServiceFeing implements ItemService {

    private final ProductClientRest clientRest;

    @Autowired
    public ItemServiceFeing(ProductClientRest clientRest) {
        this.clientRest = clientRest;
    }

    @Override
    public List<Item> findAllItems() {
        return clientRest.getProducts()
                .stream()
                .map(p -> new Item(p, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findItemById(Long id, Integer count) {
        Product product = clientRest.getProduct(id);
        return new Item(product, count);
    }

    @Override
    public Product createProduct(Product product) {
        return clientRest.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return clientRest.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        clientRest.deleteProduct(id);
    }
}
