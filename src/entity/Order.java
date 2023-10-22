package entity;

import Utils.FileUtils;

import java.util.Random;
import java.util.Scanner;

public class Order {
        private int orderId;
        private String userLogin;
        private Cart productInOrder;

        private double totalPrice;


        public Order(int orderId, String userLogin, Cart cart) {
            this.orderId = orderId;
            this.userLogin = userLogin;
            this.productInOrder = cart;
            this.totalPrice = getTotalPrice();
        }

        public int getOrderId() {
            return orderId;
        }

        public String getUserLogin() {
            return userLogin;
        }

        public Cart getProductInOrder() {
            return productInOrder;
        }
    public double getTotalPrice() {
        double total = 0.0;
        for (Product product : productInOrder.getProducts()) {
            total += product.getPrice();
        }
        return total;
    }

    public static int generateUniqueOrderId() {
        Random random = new Random();
        int min = 50;
        int step = 50;
        int orderId = min + step * random.nextInt(50);
        return orderId;
    }

    public static List<Order> getOrdersByUserLogin(String userLogin,List<Order> allOrders, List<Order> userOrders) {
            for (Order order: allOrders){
                if (userLogin.equals(order.getUserLogin())) {
                    userOrders.insert(order);
                }
            }
            return userOrders;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Номер заказа: ").append(orderId).append("\n");
        stringBuilder.append("Пользователь создавший заказ: ").append(userLogin).append("\n");
        stringBuilder.append("Товары в заказе:\n");
        for (Product product : productInOrder.getProducts()) {
            stringBuilder.append(product).append("\n");
        }
        return stringBuilder.toString();
    }



    public static void makeOrder(String userLogin, Cart cart,List<Order> orders) {
        if (cart.getProducts().getSize() == 0) {
            System.out.println("Корзина пуста, заказ не может быть оформлен.");
            return;
        }
        int orderId = generateUniqueOrderId();
        Order order = new Order(orderId, userLogin, cart);
        System.out.println("Ваш заказ успешно оформлен под номером " + orderId + ".");
        System.out.println("Сумма заказа: " + order.getTotalPrice());
        orders.insert(order);
    }

    public static Order findOrderById(int orderId,List<Order> orders) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public static List <Order> removeOrder(int orderId, List<Order> orders) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                orders.remove(order);
                System.out.println("Заказ успешно удалён");
                return orders;
            }
        }
        System.out.println("Заказ с таким ID не найдён");
        return orders;
    }

    public static void removeProductFromOrder(int removeProductId, Cart cart,List<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == removeProductId) {
                cart.removeProduct(product);
                System.out.println("Товар удален из заказа.");
                return;
            }
        }
        System.out.println("Товар с указанным ID не найден.");
    }

    public static void addProductToOrder(int productId, Cart cartInOrder, List<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                cartInOrder.addProduct(product);
                System.out.println("Товар добавлен в заказ.");
                return;
            }
        }
        System.out.println("Товар с указанным ID не найден.");
    }
}
