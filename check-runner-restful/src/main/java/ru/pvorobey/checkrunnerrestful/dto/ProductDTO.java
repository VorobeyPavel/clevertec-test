package ru.pvorobey.checkrunnerrestful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long id;
    private String name;
    private double price;
    private int count;
    private double finalPrice;

    public ProductDTO(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
