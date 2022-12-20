package ru.pvorobey.checkrunnerconsole.view;

import ru.pvorobey.checkrunnerconsole.bean.Product;

import java.util.ArrayList;

public interface Print {

    void printCheckToConsole(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    void printCheckToFile(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    StringBuilder printCheckToEmail(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);

    String checkHeader();

    StringBuilder bodyCheck(ArrayList<Product> listProductAndPrice, double quantityDiscount, int numberCard);
}
