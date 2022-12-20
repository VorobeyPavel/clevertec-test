package ru.pvorobey.checkrunnerconsole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pvorobey.checkrunnerconsole.bean.Product;
import ru.pvorobey.checkrunnerconsole.bean.ProductBuilder;
import ru.pvorobey.checkrunnerconsole.dao.Dao;


import java.util.ArrayList;

@Component
public class ServiceImpl implements Service{

    Dao dao;

    @Autowired
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }


    @Override
    public ArrayList<Product> getWarehouse() {
        return dao.getWarehouse();
    }

    @Override
    public ArrayList<Product> getWarehouseDB() {
        ArrayList<Product> list = dao.getWarehouseDB();
        System.out.println(list);
        return list;
    }

    @Override
    public ArrayList<Product> getWarehouseWithFile() {
        return dao.getWarehouseWithFile();
    }

    /*
       Метод возвращает коллекцию типа Product (список продуктов которые желает прибрести покупатель).
       Объекты Product содержат ID продукта и количество приобретаемого продукта.
       */
    @Override
    public ArrayList<Product> shoppingList(String productLine) {
        ArrayList<Product> listProducts = new ArrayList<>();

        String[] idAndCount = productLine.split(" ");
        for (String s : idAndCount) {
            int index = s.indexOf("-");
            try {
                if (!s.substring(0, index).equals("card")){

                    int id = Integer.parseInt(s.substring(0,index));
                    int count = Integer.parseInt(s.substring(++index));

                    //Product product = new Product(id, count);
                    Product product = new ProductBuilder().setId(id).setCount(count).getProduct();
                    listProducts.add(product);
                }
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("Вы ввели неверный формат данных. Допустимый формат данных: 3-2 2-5 5-3 card-1234");
                System.exit(0);
            } catch (NumberFormatException | IndexOutOfBoundsException e){
                System.out.println("Вы ввели недоступный ID товара или неверный формат данных количества товара. " +
                        "Значение дисконтной карты начинается со слова card.\n" +
                        "ID может иметь значение от 1 до 10. Количество товара целочисленное значение. ");
                System.exit(0);
            }
        }
        return listProducts;
    }

    /*
    Метод возвращает номер дисконтной карты, если она введена корректно. Дисконтная карта дает общую скидку 5%.
    В противном случает общая цена будет подсчитана без скидки.
    */
    @Override
    public int getNumberCard(String productLine) {
        String[] idAndCount = productLine.split(" ");
        int numberCard = 0;

        for (String s : idAndCount) {
            int index = s.indexOf("-");
            if (s.substring(0, index).equals("card")) {
                try {
                    numberCard = Integer.parseInt(s.substring(++index));
                }catch (NumberFormatException e){
                    System.out.println("Дисконтная карта не считана. Номер дисконтной карты должен иметь целочисленное значение.");
                    return 0;
                }
            }
        }
        return numberCard;
    }

    /*
    Данный метод проверяет есть ли в наличие id желаемого товар для покупателя на складе.
    */
    @Override
    public boolean productAvailabilityCheck(ArrayList<Product> shoppingList, ArrayList<Product> warehouse){

        boolean productAvailability = false;

        for (Product product : shoppingList) {
            int idProduct = product.getId();

            for (Product product1 : warehouse) {
                productAvailability = false;
                if (product1.getId() == idProduct) {
                    productAvailability = true;
                    break;
                }
            }
            if (!productAvailability){
                System.out.println("Вы ввели недоступный ID товара или неверный формат данных количества товара.\n" +
                        "ID может иметь значение от 1 до 10. Количество товара целочисленное значение.");
                System.exit(0);
                return false;
            }
        }
        return productAvailability;
    }

    /*
    Метод возвращает коллекцию товаров с ценой каждого товара и общей ценой каждой позиции (учитывая количество заказов).
    */
    @Override
    public ArrayList<Product> priceProduct(ArrayList<Product> shoppingList, ArrayList<Product> warehouse) {

        productAvailabilityCheck(shoppingList, warehouse);

        ArrayList<Product> listProductAndPrice = new ArrayList<>();

        for (Product product : shoppingList) {
            int idProduct = product.getId();

            double priceProduct = 0;
            double priceTotal = 0;
            String nameProduct = null;

            for (Product product1 : warehouse) {
                if (product1.getId()==idProduct){
                    priceProduct = product1.getPrice();
                    nameProduct = product1.getName();
                }
            }
            priceTotal = priceProduct*product.getCount();
            listProductAndPrice.add(new Product(product.getId(), nameProduct, priceProduct, product.getCount(), priceTotal));
        }
        return listProductAndPrice;
    }

    /*
    Метод предоставляет скидку по количеству заказанных товаров. Если количество кого либо приобретаемого товара больше 5,
    то по данной позиции предоставляется скидка 10%.
    */
    @Override
    public double quantityDiscount (ArrayList<Product> shoppingList) {
        double quantityDiscount = 0;
        for (Product product : shoppingList) {
            if (product.getCount()>5){
                quantityDiscount += product.getCount()*product.getPrice()*0.1;
            }
        }
        return quantityDiscount;
    }

}
