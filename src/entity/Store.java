package entity;

import Utils.FileUtils;

public class Store {
    private static List<Product> productList;
    private static List<User> users;
    private static List<Cart> carts=new List<>(new Cart[10]);

    private static User currentUser;


    public Store(List<User> users, List<Cart> carts, User user) {
        this.productList = new List<>(new Product[10]);
        this.users = users;
        this.carts = carts;
        this.currentUser = user;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void printProductList() {
        System.out.println("Список товаров:");
        for (int i = 0; i < productList.getSize(); i++) {
            Product product = productList.getAll()[i];
            System.out.println(product);
        }
    }


    public static void addProductToCart(Product product) {
        if (currentUser != null) {
            for (int i = 0; i < users.getSize(); i++) {
                if (users.getAll()[i].equals(currentUser)) {
                    Cart cart = carts.getAll()[i];
                    cart.addProduct(product);
                    System.out.println("Товар добавлен в корзину.");
                    return;
                }
            }
        } else {
            System.out.println("Пользователь не установлен. Выберите пользователя с помощью setCurrentUser.");
        }
    }

    public long generateUniqueOrderId() {
        long keyOrderId = System.currentTimeMillis();
        long orderId = keyOrderId / 1000000;
        return orderId;
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

                System.out.println("Ваш заказ успешно оформлен под номером " + generateUniqueOrderId()  + ".");
                System.out.println("Сумма заказа: " + totalPrice);
                return;
            }
        }
    }
}
