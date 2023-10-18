package Utils;

import entity.List;
import entity.Product;
import entity.Store;
import entity.User;

import java.io.*;

public class FileUtils {

    static String[] getPartsOfLine(String line) {
        return line.split(",");
    }


    public static void readUserFile(List<User> listUser, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                User user = new User(partsOfLine[0], partsOfLine[1]);

                listUser.insert(user);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception er) {
                System.out.println("Произошла ошибка");
            }
            throw new RuntimeException("Такой файл не найден");
        }
    }

    public static void readProductFile(List<Product> products) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/productList.csv"))) {
            String line;
            boolean firstLine = true;


            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 3) {
                    try {
                        int id = Integer.parseInt(data[0]);
                        String name = data[1];
                        double price = Double.parseDouble(data[2]);
                        Store.addProductToCart(new Product(id, name, price));
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка при чтении данных: " + e.getMessage());
                    }
                } else {
                    System.out.println("Некорректные данные: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла productList.csv: " + e.getMessage());
        }
    }


    public static void writeUserToFile(List<User> listUser, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            User[] users = listUser.getAll();

            for (int i = 0; i < listUser.getSize(); i++) {
                bufferedWriter.write(users[i].getLogin() + "," + users[i].getPassword() + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeProductToFile(List<Product> listProduct, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            Product[] products = listProduct.getAll();

            for (int i = 0; i < listProduct.getSize(); i++) {
                bufferedWriter.write(products[i].getId() + "," + products[i].getName() +"," + products[i].getPrice() + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
