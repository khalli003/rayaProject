package entity;

import Utils.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Store {
    private static List<Product> productList;
    private List<User> users;
    private List<Cart> carts;
    private int lastOrderId;

    public Store(List<User> users, List<Cart> carts) {
        this.productList = new List<>(new Product[10]);
        this.users = users;
        this.carts = carts;
        this.lastOrderId = 0;
        FileUtils.readProductFile();
    }

    public static void addProduct(Product product) {
        productList.insert(product);
    }

    public void printProductList() {
        System.out.println("Список товаров:");
        for (int i = 0; i < productList.getSize(); i++) {
            Product product = productList.getAll()[i];
            System.out.println(product);
        }
    }


    public void addProductToCart(User user, int productId) {
        for (int i = 0; i < productList.getSize(); i++) {
            Product product = productList.getAll()[i];
            if (product.getId() == productId) {
                for (int j = 0; j < users.getSize(); j++) {
                    if (users.getAll()[j] == user) {
                        Cart cart = carts.getAll()[j];
                        cart.addProduct(product);
                        System.out.println("Товар добавлен в корзину.");
                        return;
                    }
                }
            }
        }
        System.out.println("Товар с указанным ID не найден.");
    }

    public void removeProductFromCart(User user, int removeProductId) {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.getAll()[i] == user) {
                Cart cart = carts.getAll()[i];
                for (int j = 0; j < cart.getProducts().getSize(); j++) {
                    Product product = cart.getProducts().getAll()[j];
                    if (product.getId() == removeProductId) {
                        cart.removeProduct(product);
                        System.out.println("Товар удален из корзины.");
                        return;
                    }
                }
            }
        }
        System.out.println("Товар с указанным ID не найден в корзине.");
    }

    public void printCart(User user) {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.getAll()[i] == user) {
                Cart cart = carts.getAll()[i];
                System.out.println("Товары в корзине:");
                for (int j = 0; j < cart.getProducts().getSize(); j++) {
                    Product product = cart.getProducts().getAll()[j];
                    System.out.println(product);
                }
                return;
            }
        }
    }

    public void placeOrder(User user) {
        for (int i = 0; i < users.getSize(); i++) {
            if (users.getAll()[i] == user) {
                Cart cart = carts.getAll()[i];
                if (cart.getProducts().getSize() == 0) {
                    System.out.println("Корзина пуста, заказ не может быть оформлен.");
                    return;
                }

                double totalPrice = 0;
                for (int j = 0; j < cart.getProducts().getSize(); j++) {
                    Product product = cart.getProducts().getAll()[j];
                    totalPrice += product.getPrice();
                }

                lastOrderId++;
                System.out.println("Ваш заказ успешно оформлен под номером " + lastOrderId + ".");
                System.out.println("Сумма заказа: " + totalPrice);
                return;
            }
        }
    }
}
