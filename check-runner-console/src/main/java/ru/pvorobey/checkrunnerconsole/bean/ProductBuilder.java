package ru.pvorobey.checkrunnerconsole.bean;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBuilder {

    int id;
    String name;
    double priceProduct;
    int count;
    double priceTotal;

    public ProductBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
        return this;
    }

    public ProductBuilder setCount(int count) {
        this.count = count;
        return this;
    }

    public ProductBuilder setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }

    public Product getProduct(){
        return new Product(id, name, priceProduct, count, priceTotal);
    }
}
