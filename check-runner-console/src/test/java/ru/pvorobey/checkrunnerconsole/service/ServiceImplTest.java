package ru.pvorobey.checkrunnerconsole.service;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.pvorobey.checkrunnerconsole.bean.Product;
import ru.pvorobey.checkrunnerconsole.dao.Dao;
import ru.pvorobey.checkrunnerconsole.dao.DaoImpl;

import java.util.ArrayList;
import java.util.List;

class ServiceImplTest {

    @Autowired
    private  JdbcTemplate jdbcTemplate;
    private Dao dao;
    private Service service;

    @BeforeEach
    void setUp() {
        dao = new DaoImpl(jdbcTemplate);
        service = new ServiceImpl(dao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getWarehouse() {
        long productQuantity = 10;
        List<Product> list = service.getWarehouse();
        Assertions.assertEquals(productQuantity, list.size());
    }

    @Test
    void getWarehouseDB() {
    }

    @Test
    void getWarehouseWithFile() {
        Assert.assertThrows(NullPointerException.class, ()-> service.getWarehouseWithFile());
    }

    @Test
    void shoppingList() {
        String productLine = "3-2 2-5 5-3 card-1234";
        List<Product> list = service.shoppingList(productLine);
        Assertions.assertEquals(3, list.size());

    }

    @Test
    void getNumberCard() {
        String productLine = "3-2 2-5 5-3 card-1234";
        Assertions.assertEquals(1234, service.getNumberCard(productLine));
    }

    @Test
    void getNumberCardNoCard() {
        String productLine = "3-2 2-5 5-3 card-hello";
        Assertions.assertEquals(0, service.getNumberCard(productLine));
    }

    @Test
    void productAvailabilityCheck() {
        String productLine = "3-2 2-8 5-3 card-1234";
        ArrayList<Product> listProduct = service.shoppingList(productLine);
        ArrayList<Product> shoppingList = service.getWarehouse();

        Assertions.assertTrue(service.productAvailabilityCheck(listProduct, shoppingList));
    }



    @Test
    void priceProduct() {
        ArrayList<Product> shoppingList = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();

        List<Product> priceProduct = service.priceProduct(shoppingList, listProduct);
        Assertions.assertEquals(0, priceProduct.size());
    }

    @Test
    void priceProduct2() {
        String productLine = "3-2 2-5 5-3 card-1234";
        ArrayList<Product> listProduct = service.shoppingList(productLine);
        ArrayList<Product> shoppingList = service.getWarehouse();

        List<Product> priceProduct = service.priceProduct(listProduct, shoppingList);

        Assertions.assertEquals(3, priceProduct.size());
    }

    @Test
    void quantityDiscount() {
        String productLine = "3-2 2-8 5-3 card-1234";
        ArrayList<Product> listProduct = service.shoppingList(productLine);
        ArrayList<Product> listProductAndPrice = service.priceProduct(listProduct, service.getWarehouse());

        double quantityDiscount = service.quantityDiscount(listProductAndPrice);

        Assertions.assertNotEquals(0, quantityDiscount);
    }
}