package ru.pvorobey.checkrunnerrestful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pvorobey.checkrunnerrestful.dao.CardRepository;
import ru.pvorobey.checkrunnerrestful.dao.ProductRepository;
import ru.pvorobey.checkrunnerrestful.dto.CardDTO;
import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;
import ru.pvorobey.checkrunnerrestful.entity.Card;
import ru.pvorobey.checkrunnerrestful.entity.Product;
import ru.pvorobey.checkrunnerrestful.exeptions.IdTypeCardNotFoundException;
import ru.pvorobey.checkrunnerrestful.exeptions.IdTypeProductNotFoundException;
import ru.pvorobey.checkrunnerrestful.service.mapper.CardDTOMapper;
import ru.pvorobey.checkrunnerrestful.service.mapper.ProductDTOMapper;

import java.util.List;


@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ProductDTOMapper productDTOMapper;
    @Autowired
    CardDTOMapper cardDTOMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CardRepository cardRepository;


    @Override
    public List<Product> getAllProducts() {
        List<Product> list = productRepository.findAll();
        return list;
    }

    @Override
    public CardDTO getCard(String number) {
        Card card = cardRepository.findByNumber(number).orElse(new Card(0L, "0", 0));
        return cardDTOMapper.toCardDTO(card);
    }

    @Override
    public List<ProductDTO> getListProductAndPrice(List<ProductDTO> products) {

        for (ProductDTO product : products) {
            Product productDB = productRepository.findById(product.getId()).orElseThrow(()->
            new IdTypeProductNotFoundException("Product with id = " + product.getId() + " not found!"));
            product.setName(productDB.getName());
            product.setPrice(productDB.getPrice());
            product.setFinalPrice(productDB.getPrice()*product.getCount());
        }
        return products;
    }

    @Override
    public ProductDTO getProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new IdTypeProductNotFoundException("Product with id = " + id + " not found!"));
        return productDTOMapper.toProductDTO(product);
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getId()!=null){
            productRepository.findById(product.getId()).orElseThrow(()->
                    new IdTypeProductNotFoundException("Product with id = " + product.getId() + " not found!"));
        }
        productRepository.save(product);
    }

    @Override
    public void saveCard(Card card) {
        if (card.getId()!=null){
            cardRepository.findById(card.getId()).orElseThrow(()->
                    new IdTypeCardNotFoundException("Card with id = " + card.getId() + " not found!"));
        }
        cardRepository.save(card);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id).orElseThrow(() ->
                new IdTypeProductNotFoundException("Product with id = " + id + " not found!"));
        productRepository.deleteById(id);
    }

    @Override
    public void deleteCard(long id) {
        cardRepository.findById(id).orElseThrow(() ->
                new IdTypeCardNotFoundException("Card with id = " + id + " not found!"));
        cardRepository.deleteById(id);
    }
}
