package ru.pvorobey.checkrunnerconsole;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.pvorobey.checkrunnerconsole.bean.Product;
import ru.pvorobey.checkrunnerconsole.dao.Dao;
import ru.pvorobey.checkrunnerconsole.service.MailService;
import ru.pvorobey.checkrunnerconsole.service.Service;
import ru.pvorobey.checkrunnerconsole.view.Print;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckRunnerConsole {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.pvorobey.checkrunnerconsole");

        Dao dao = context.getBean(Dao.class);
        MailService mailService = context.getBean(MailService.class);
        Print print = context.getBean(Print.class);
        Service service = context.getBean(Service.class);


        System.out.println("Введите набор параметров в формате itemId-quantity+номер дисконтной карты" +
                "\n(itemId - идентификатор товара, quantity - его количество)" +
                "\nпример для ввода: 3-2 2-5 5-3 card-1234");

        Scanner sc = new Scanner(System.in);
        String productLine = sc.nextLine();
        ArrayList<Product> listProduct = service.shoppingList(productLine);

        /*
        В зависимости от того откуда мы хотим брать доступные для заказа товары(из коллекции в классе DaoImpl,
        из файла или из БД, используем соответствующий параметр в методе.
        */

        // Вариант получения товаров из коллекции в классе DaoImpl. Используем dao.getWarehouse()
        //ArrayList<Product> listProductAndPrice = service.priceProduct(listProduct, service.getWarehouse());

        //Вариант получения товаров из файла. Используем getWarehouseWithFile()
        //ArrayList<Product> listProductAndPrice = service.priceProduct(listProduct, service.getWarehouseWithFile());

        //Вариант получения товаров из базы данных. Используем getWarehouseDB()
        ArrayList<Product> listProductAndPrice = service.priceProduct(listProduct, service.getWarehouseDB());


        /*Получаем скидку по количеству заказанных товаров. Если количество кого либо приобретаемого товара больше 5,
        то по данной позиции предоставляется скидка 10%.*/
        double quantityDiscount = service.quantityDiscount(listProductAndPrice);

        int numberCard = service.getNumberCard(productLine);


        // Метод для вывода чека в консоль
        print.printCheckToConsole(listProductAndPrice, quantityDiscount, numberCard);

        // Метод для отправки чека на Email
        mailService.sendEmail(mailService.setToMail(), "Check", String.valueOf(print.printCheckToEmail(listProductAndPrice, quantityDiscount, numberCard)));

        //Метод для вывода чека в файл
        print.printCheckToFile(listProductAndPrice, quantityDiscount, numberCard);

    }
}
