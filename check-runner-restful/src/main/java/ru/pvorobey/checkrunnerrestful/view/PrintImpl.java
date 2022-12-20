package ru.pvorobey.checkrunnerrestful.view;

import org.springframework.stereotype.Component;
import ru.pvorobey.checkrunnerrestful.dto.ProductDTO;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PrintImpl implements Print{

    String headerCheck = checkHeader();

    /*
    Метод распечатывает чек в консоль
    */
    @Override
    public void printCheckToConsole(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard){
        System.out.println(headerCheck);
        System.out.println(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
    }

    /*
    Метод распечатывает чек в файл
    */
    @Override
    public void printCheckToFile(ArrayList<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard) {

        try (FileWriter fileWriter = new FileWriter("checkRunner.txt", false)){

            fileWriter.write(headerCheck);
            String bodyCheck = String.valueOf(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
            fileWriter.write(bodyCheck);
            fileWriter.flush();
        }catch (IOException e){
            System.out.println("Файла не существует");
        }
    }

    @Override
    public StringBuilder printCheckToEmail(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard) {

        StringBuilder result = new StringBuilder();
        result.append(headerCheck);
        result.append(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
        return result;
    }

    @Override
    public StringBuilder printCheckToResponse(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard) {

        StringBuilder result = new StringBuilder();
        result.append(headerCheck);
        result.append(bodyCheck(listProductAndPrice, quantityDiscount, numberCard));
        return result;
    }

    @Override
    public String checkHeader() {
        int cashier = (int) (Math.random() * 10000);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formater2 = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        return "            CASH RECEIPT\n" +
                "           supermarket 123\n" +
                "          Tel :123-456-789\n" +
                "CASHIER: №"+cashier + "       DATE: "+formater.format(date)+"\n"+
                "                     TIME: "+formater2.format(date)+"\n" +
                "-------------------------------------"+"\n" +
                "QTY DESCRIPTION        PRICE    TOTAL"+"\n";
    }

    @Override
    public StringBuilder bodyCheck(List<ProductDTO> listProductAndPrice, double quantityDiscount, int numberCard) {

        StringBuilder bodyCheck = new StringBuilder();
        double total = 0;
        double taxableTot = 0;
        DecimalFormat decimalFormat = new DecimalFormat( "#.##" );

        for (ProductDTO product : listProductAndPrice) {
            total+=product.getFinalPrice();

            String nameProduct = product.getName();
            int lengthName = nameProduct.length();
            if (lengthName>16){
                nameProduct = nameProduct.substring(0,16);
            }
            if (lengthName<16){
                while (lengthName<16){
                    nameProduct = nameProduct + " ";
                    ++lengthName;
                }
            }
            String priceProduct = "$"+(decimalFormat.format(product.getPrice()));
            int length = priceProduct.length();
            if (length<7){
                while (length<7){
                    priceProduct = " "+priceProduct;
                    length++;
                }
            }
            String priceTotal = "$"+(decimalFormat.format(product.getFinalPrice()));
            int lengthTotal = priceTotal.length();
            if (lengthTotal<8){
                while (lengthTotal<8){
                    priceTotal = " "+priceTotal;
                    lengthTotal++;
                }
            }
            bodyCheck.append(" "+product.getCount()+"  "+nameProduct+" "+priceProduct+" "+priceTotal+"\n");
        }
        bodyCheck.append("=====================================\n");

        double totalDiscount = quantityDiscount;
        if (numberCard!=0){
            totalDiscount+=total*0.05;
        }

        taxableTot = total-totalDiscount;

        String taxableTotString = "$"+decimalFormat.format(taxableTot);
        if ((""+taxableTot).length()<24){
            while (taxableTotString.length()<24){
                taxableTotString = " "+taxableTotString;
            }
        }
        bodyCheck.append("TAXABLE TOT. "+taxableTotString+"\n");

        String discount = "$" + decimalFormat.format(totalDiscount);
        if ((""+discount).length()<28){
            while (discount.length()<28){
                discount = " "+discount;
            }
        }
        bodyCheck.append("Discount "+discount+"\n");

        String totalString = "$" + decimalFormat.format(total);
        if ((""+totalString).length()<31){
            while (totalString.length()<31){
                totalString = " "+totalString;
            }
        }
        bodyCheck.append("TOTAL "+totalString+"\n");

        return bodyCheck;
    }
}
