package ru.pvorobey.checkrunnerrestful.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;
import ru.pvorobey.checkrunnerrestful.entity.Product;
import ru.pvorobey.checkrunnerrestful.service.mapper.ProductDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDTOMapperImpl implements ProductDTOMapper {

    @Override
    public ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())

                .build();
    }

    @Override
    public List<ProductDTO> toListProductDTO(List<Product> products) {
        return products.stream().map(this::toProductDTO).collect(Collectors.toList());
    }

}











