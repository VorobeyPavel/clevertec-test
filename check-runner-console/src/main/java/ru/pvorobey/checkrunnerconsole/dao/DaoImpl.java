package ru.pvorobey.checkrunnerconsole.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pvorobey.checkrunnerconsole.bean.Product;
import ru.pvorobey.checkrunnerconsole.bean.ProductBuilder;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
public class DaoImpl implements Dao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Заполняем таблицу в БД
    public void save(ArrayList<Product> list) {
        for (Product product : list) {
            jdbcTemplate.update("INSERT INTO products VALUES(?, ?, ?)",
                    product.getId(), product.getName(), product.getPrice());
        }
    }

    /*
    Метод для получения доступных для заказа товаров из базы данных.
    */
    @Override
    public ArrayList<Product> getWarehouseDB(){
        return (ArrayList<Product>) jdbcTemplate.query("SELECT * FROM products",
                new BeanPropertyRowMapper<>(Product.class));
    }

    /*
    Метод формирует коллекцию доступных для заказа товаров.
    */
    @Override
    public ArrayList<Product> getWarehouse() {
        ArrayList<Product> warehouse = new ArrayList<>();
        warehouse.add(new ProductBuilder().setId(1).setName("Avocado").setPriceProduct(7.99).getProduct());
        warehouse.add(new ProductBuilder().setId(2).setName("Pineapple").setPriceProduct(5.29).getProduct());
        warehouse.add(new ProductBuilder().setId(3).setName("Orange").setPriceProduct(3.49).getProduct());
        warehouse.add(new ProductBuilder().setId(4).setName("Banana").setPriceProduct(3.69).getProduct());
        warehouse.add(new ProductBuilder().setId(5).setName("Grape").setPriceProduct(7.79).getProduct());
        warehouse.add(new ProductBuilder().setId(6).setName("Pear").setPriceProduct(4.39).getProduct());
        warehouse.add(new ProductBuilder().setId(7).setName("Melon").setPriceProduct(5.49).getProduct());
        warehouse.add(new ProductBuilder().setId(8).setName("Kiwi").setPriceProduct(4.49).getProduct());
        warehouse.add(new ProductBuilder().setId(9).setName("Lemon").setPriceProduct(3.99).getProduct());
        warehouse.add(new ProductBuilder().setId(10).setName("Mandarin").setPriceProduct(4.29).getProduct());
        return warehouse;
    }

    /*
    Метод формирует коллекцию доступных для заказа товаров полученных из файла.
    Файл хранит данные с следующем формате: id-nameProduct-price (1-Avocado-7.99).
    id - номер товара на складе. nameProduct - название товара. price - цена.
    */
    @Override
    public ArrayList<Product> getWarehouseWithFile(){
        ArrayList<Product> warehouse = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к файлу с товарами");

        try {
            String nameFile = bufferedReader.readLine();

            if (!nameFile.matches("([A-Z|a-z]://[^*|\"<>?\\n]*)|(////.*?//.*)+")){
                System.out.println("Файла не существует. Введите корректный путь к файлу(для Windows). " +
                        "Примерный вариант: C://Users//Pavel//Desktop//Warehouse.txt");
                System.exit(0);
            }

            FileReader fileReader = new FileReader(nameFile);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader);

            while (bufferedReader1.ready()){
                String productLine = bufferedReader1.readLine();

                String [] products = productLine.split("-");
                int idProduct = Integer.parseInt(products[0]);
                String nameProduct = products[1];
                double priceProduct = Double.parseDouble(products[2]);
                //Product product = new Product(idProduct, nameProduct, priceProduct);
                Product product = new ProductBuilder().setId(idProduct).setName(nameProduct)
                        .setPriceProduct(priceProduct).getProduct();
                warehouse.add(product);
            }
        }catch (IOException e){
            System.out.println("Файла не существует. Введите корректное имя файла");
            return null;
        }
        return warehouse;
    }



}
