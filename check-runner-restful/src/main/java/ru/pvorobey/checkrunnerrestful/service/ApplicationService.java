package ru.pvorobey.checkrunnerrestful.service;

import ru.pvorobey.checkrunnerrestful.dto.CardDTO;
import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;
import ru.pvorobey.checkrunnerrestful.entity.Card;
import ru.pvorobey.checkrunnerrestful.entity.Product;

import java.util.List;

public interface ApplicationService {

    List<Product> getAllProducts();

    List<ProductDTO> getListProductAndPrice(List<ProductDTO> products);

    CardDTO getCard(String cardNumber);

    ProductDTO getProduct(long id);

    void saveProduct(Product product);
    void saveCard(Card card);

    void deleteProduct(long id);
    void deleteCard(long id);
}
