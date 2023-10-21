package entity;

import Utils.FileUtils;

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
        long keyOrderId = System.currentTimeMillis();
        int orderId = (int) (keyOrderId / 100000000);
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
}
