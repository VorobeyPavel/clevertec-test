package ru.pvorobey.checkrunnerconsole.dao;

import ru.pvorobey.checkrunnerconsole.bean.Product;

import java.util.ArrayList;

public interface Dao {

    ArrayList<Product> getWarehouse();

    ArrayList<Product> getWarehouseDB();

    ArrayList<Product> getWarehouseWithFile();

}
