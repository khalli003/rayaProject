package Utils;

import entity.*;

import java.util.Scanner;

public class Menu {

    public static void printLoginMenu(){
        System.out.println("""
                Выберите действие:
                1 Войди.
                2 Зарегистрироваться.
                3 Выход.
                """);
    }

    public static void printUserMenu() {
        System.out.println(
                """
                        Выберите действие:
                        1. Посмотреть список товаров
                        2. Добавить товар в корзину
                        3. Удалить товар из корзины
                        4. Найти товар по названию
                        5. Посмотреть корзину
                        6. Просмотреть заказы
                        7. Сделать заказ
                        8. Редактировать заказ
                        9. Увидеть данные о себе
                        10. Изменить данные о себе
                        11. Выйти 
                                """);
    }

    public static void printRedactOrderMenu(){
        System.out.println("""
                1. Добавить товар в заказ
                2. Удалить товар из заказа
                3. Удалить заказ
                """);
    }

    public static void appMenu(Scanner scanner,List<User> users, User user,List <Product> productList,Cart cart,List<Order> allOrders,List <Order> userOrders) {
         Product product = new Product(1,"nameless",1.0);
        while (true) {
            printUserMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    productList.print();
                    break;
                case 2:
                    System.out.println("Введите ID товара:");
                    int productId = scanner.nextInt();
                    Cart.addProductToCart(productId,cart,productList);
                    break;
                case 3:
                    System.out.println("Введите ID товара:");
                    int removeProductId = scanner.nextInt();
                    cart.removeProductFromCart(removeProductId,cart);
                    break;
                case 4:
                    System.out.println("Введите название товара");
                    String searchedProductName = scanner.nextLine();
                    Product searchedProduct = Product.searchProductByName(productList,searchedProductName);
                    if (searchedProduct !=null){
                        System.out.println(searchedProduct);
                    }else {
                        System.out.println("К сожалению, товар с таким названием не найден");
                    }
                    break;
                case 5:
                    cart.printCart(cart);
                    break;
                case 6:
                     userOrders = Order.getOrdersByUserLogin(user.getLogin(),allOrders,userOrders);
                    System.out.println("Список заказов пользователя ");
                    for (Order order : userOrders) {
                        System.out.println(order);
                    }
                    break;
                case 7:
                    Order.makeOrder(user.getLogin(), cart, allOrders);
                    break;
                case 8:
                    if (userOrders.getSize() > 0) {
                        redactOrderMenu(scanner, productList, userOrders);
                    } else {
                        System.out.println("У вас нет заказов для редактирования.");
                    }
                    break;
                case 9:
                    System.out.println(user);
                    break;
                case 10:
                    System.out.print("""
                            Введите через ентер следующие поля :
                            1. Логин
                            2. Пароль
                            """);
                    scanner.nextLine();
                    String login = scanner.nextLine();
                    String password = scanner.nextLine();
                    User newUser = new User(login,password);
                    users.replaceElement(user, newUser);
                    FileUtils.writeUserToFile(users, "src/users.csv");
                    break;
                case 11:
                    System.out.println("Выход из аккаунта.");
                    return;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");

            }
        }
    }
    public static void redactOrderMenu(Scanner scanner, List<Product> productList,List <Order> userOrders) {
        while (true) {
            printRedactOrderMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите номер заказа:");
                    int orderIdToAddTo = scanner.nextInt();
                    Order orderToAddTo = Order.findOrderById(orderIdToAddTo,userOrders);

                    if (orderToAddTo != null) {
                        System.out.println("Введите ID товара:");
                        int productId = scanner.nextInt();
                            orderToAddTo.addProductToOrder(productId,orderToAddTo.getProductInOrder(),productList);
                            System.out.println("Товар успешно добавлен в заказ.");
                    } else {
                        System.out.println("Заказ с указанным номером не найден.");
                    }
                    break;

                case 2:
                    System.out.println("Введите номер заказа:");
                    int orderIdToRemoveFrom = scanner.nextInt();
                    Order orderToRemoveFrom = Order.findOrderById(orderIdToRemoveFrom,userOrders);

                    if (orderToRemoveFrom != null) {
                        System.out.println("Введите ID товара:");
                        int productId = scanner.nextInt();
                        orderToRemoveFrom.removeProductFromOrder(productId,orderToRemoveFrom.getProductInOrder(),productList);
                        System.out.println("Товар успешно добавлен в заказ.");
                    } else {
                        System.out.println("Заказ с указанным номером не найден.");
                    }
                    break;
                case 3:
                    System.out.println("Введите номер заказа, который нужно удалить:");
                    int orderIdToDelete = scanner.nextInt();
                    userOrders = Order.removeOrder(orderIdToDelete,userOrders);
                    break;

                case 4:
                    return;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
            }
        }
    }
}
