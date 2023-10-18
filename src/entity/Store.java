package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Store {
    private List<Product> productList;
    private HashMap<User, Cart> carts;
    private int lastOrderId;

    public Store() {
        this.productList = new ArrayList<>();
        this.carts = new HashMap<>();
        this.lastOrderId = 0;

        loadProductList();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }



    public void loadProductList() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/productList.csv"))) {
            String line;
            boolean firstLine = true;


            while ((line = reader.readLine()) != null) {
                if (firstLine){
                    firstLine =false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length >= 3) { // Убедимся, что есть как минимум 3 элемента
                    try {
                        int id = Integer.parseInt(data[0]);
                        String name = data[1];
                        double price = Double.parseDouble(data[2]);
                        addProduct(new Product(id, name, price));
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


    public void saveProductList() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/productList.csv"))) {
            for (Product product : productList) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла productList.csv");
        }
    }

    public void printProductList() {
        System.out.println("Список товаров:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public User login() {
        System.out.println("Введите логин:");
        String login = new Scanner(System.in).nextLine();

        System.out.println("Введите пароль:");
        String password = new Scanner(System.in).nextLine();

        for (User user : carts.keySet()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public User register() {
        System.out.println("Введите логин:");
        String login = new Scanner(System.in).nextLine();

        System.out.println("Введите пароль:");
        String password = new Scanner(System.in).nextLine();

        User newUser = new User(login, password);
        Cart newCart = new Cart();
        carts.put(newUser, newCart);

        return newUser;
    }

    public void addProductToCart(User user, int productId) {
        Cart cart = carts.get(user);
        for (Product product : productList) {
            if (product.getId() == productId) {
                cart.addProduct(product);
                System.out.println("Товар добавлен в корзину.");
                return;
            }
        }
        System.out.println("Товар с указанным ID не найден.");
    }

    public void removeProductFromCart(User user, int removeProductId) {
        Cart cart = carts.get(user);
        for (Product product : cart.getProducts()) {
            if (product.getId() == removeProductId) {
                cart.removeProduct(product);
                System.out.println("Товар удален из корзины.");
                return;
            }
        }
        System.out.println("Товар с указанным ID не найден в корзине.");
    }

    public void printCart(User user) {
        Cart cart = carts.get(user);
        System.out.println("Товары в корзине:");
        for (Product product : cart.getProducts()) {
            System.out.println(product);
        }
    }

    public void placeOrder(User user) {
        Cart cart = carts.get(user);
        if (cart.getProducts().size() == 0) {
            System.out.println("Корзина пуста, заказ не может быть оформлен.");
            return;
        }

        double totalPrice = 0;
        for (Product product : cart.getProducts()) {
            totalPrice += product.getPrice();
        }

        lastOrderId++;
        System.out.println("Ваш заказ успешно оформлен под номером " + lastOrderId + ".");
        System.out.println("Сумма заказа: " + totalPrice);
        cart.clear();
    }
}
