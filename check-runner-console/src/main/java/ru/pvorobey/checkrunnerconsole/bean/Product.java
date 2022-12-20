package ru.pvorobey.checkrunnerconsole.bean;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    int id;
    String name;
    double price;
    int count;
    double priceTotal;

}
