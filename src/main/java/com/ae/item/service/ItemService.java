package com.ae.item.service;

import com.ae.item.model.Item;
import com.ae.item.model.Product;

import java.util.List;

public interface ItemService {

    public List<Item> findAllItems();

    public Item findItemById(final Long id, final Integer count);

    public Product createProduct(final Product product);

    public Product updateProduct(final Product product);

    public void deleteProduct(final Long id);

}
