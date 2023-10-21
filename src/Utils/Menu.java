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
                        4. Посмотреть корзину
                        5. Просмотреть заказы
                        6. Сделать заказ
                        7. Увидеть данные о себе
                        8. Изменить данные о себе
                        9. Выйти 
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
                    scanner.nextLine();
                    Cart.addProductToCart(productId,cart,productList);
                    break;
                case 3:
                    System.out.println("Введите ID товара:");
                    int removeProductId = scanner.nextInt();
                    scanner.nextLine();
                    cart.removeProductFromCart(user, removeProductId,cart);
                    break;
                case 4:
                    cart.printCart(cart);
                    break;
                case 5:
                     userOrders = Order.getOrdersByUserLogin(user.getLogin(),allOrders,userOrders);
                    System.out.println("Список заказов пользователя ");
                    for (Order order : userOrders) {
                        System.out.println(order);
                    }
                    break;
                case 6:
                    Order.makeOrder(user.getLogin(), cart, allOrders);
                    break;
                case 7:
                    System.out.println(user);
                    break;
                case 8:
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
                case 9:
                    System.out.println("Выход из аккаунта.");
                    return;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
            }
        }
    }
}
