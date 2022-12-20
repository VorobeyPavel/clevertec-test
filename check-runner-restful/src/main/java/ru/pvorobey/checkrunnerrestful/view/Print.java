package ru.pvorobey.checkrunnerrestful.view;


import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public interface Print {

    void printCheckToConsole(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard);

    void printCheckToFile(ArrayList<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard);

    StringBuilder printCheckToEmail(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard);

    StringBuilder printCheckToResponse(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard);

    String checkHeader();

    StringBuilder bodyCheck(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard);
}
