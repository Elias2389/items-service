package com.ae.item.service;

import com.ae.item.model.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAllItems();
    public Item findItemById(final Long id, final Integer count);

}
