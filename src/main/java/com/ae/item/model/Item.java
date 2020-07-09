package com.ae.item.model;

import lombok.Data;

@Data
public class Item {
    private Product product;
    private Integer count;

    public Item() {
    }

    public Item(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Double getTotal() {
        return product.getPrice() * count.doubleValue();
    }

}
