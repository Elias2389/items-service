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
    public ItemServiceFeing(final ProductClientRest clientRest) {
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
    public Item findItemById(final Long id, final Integer count) {
        Product product = clientRest.getProduct(id);
        return new Item(product, count);
    }

    @Override
    public Item createProduct(final Product product) {
        Product productToItem = clientRest.createProduct(product);
        Product a = productToItem;
        return new Item(productToItem, 1);
    }

    @Override
    public Item updateProduct(final Product product) {
        Product productToItem = clientRest.updateProduct(product);
        Item item = new Item();
        item.setProduct(productToItem);
        item.setCount(1);
        return item;
    }

    @Override
    public void deleteProduct(Long id) {
        clientRest.deleteProduct(id);
    }
}
