package ru.pvorobey.checkrunnerrestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pvorobey.checkrunnerrestful.dto.CardDTO;
import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;
import ru.pvorobey.checkrunnerrestful.entity.Card;
import ru.pvorobey.checkrunnerrestful.entity.Product;
import ru.pvorobey.checkrunnerrestful.service.ApplicationService;
import ru.pvorobey.checkrunnerrestful.view.Print;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Print print;
    @Autowired
    private ApplicationService applicationService;


    @GetMapping("/allProducts")
    public List<Product> allProducts(){
        return applicationService.getAllProducts();
    }

    @PostMapping("/check")
    public StringBuilder getCheck(@RequestBody List<ProductDTO> products,
                           @RequestParam(required = false, value = "cardNumber") String cardNumber){

        List<ProductDTO> listProductAndPrice = applicationService.getListProductAndPrice(products);
        CardDTO card = applicationService.getCard(cardNumber);

        print.printCheckToConsole(listProductAndPrice, card.getDiscount(),
                Integer.parseInt(card.getNumber()));

        print.printCheckToEmail(listProductAndPrice, card.getDiscount(),
                Integer.parseInt(card.getNumber()));

        return print.printCheckToResponse(listProductAndPrice, card.getDiscount(),
                Integer.parseInt(card.getNumber()));
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct( @PathVariable long id) {
        return applicationService.getProduct(id);
    }

    @PostMapping("/save/product")
    public Object addProduct(@RequestBody Product product){
        applicationService.saveProduct(product);
        return product;
    }

    @PostMapping("/save/card")
    public Object addCard(@RequestBody Card card){
        applicationService.saveCard(card);
        return card;
    }

    @PutMapping("/save/product")
    public Product updateProduct(@RequestBody Product product){
        applicationService.saveProduct(product);
        return product;
    }

    @PutMapping("/save/card")
    public Card updateCard(@RequestBody Card card){
        applicationService.saveCard(card);
        return card;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct( @PathVariable long id) {
        applicationService.deleteProduct(id);
    }

    @DeleteMapping("/card/{id}")
    public void deleteCard( @PathVariable long id) {
        applicationService.deleteCard(id);
    }

}
