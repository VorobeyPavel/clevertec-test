package ru.pvorobey.checkrunnerrestful.service.mapper;

import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;
import ru.pvorobey.checkrunnerrestful.entity.Product;

import java.util.List;

public interface ProductDTOMapper {

    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toListProductDTO(List<Product> products);

}
