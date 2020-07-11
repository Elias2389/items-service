package com.ae.item.service;

import com.ae.item.model.Item;
import com.ae.item.model.Product;

import java.util.List;

public interface ItemService {

    public List<Item> findAllItems();

    public Item findItemById(final Long id, final Integer count);

    public Item createProduct(final Product product);

    public Item updateProduct(final Product product);

    public void deleteProduct(final Long id);

}
