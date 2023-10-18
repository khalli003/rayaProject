package Utils;

import entity.Store;
import entity.User;

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
                        5. Сделать заказ
                        6. Выйти 
                                """);
    }

    public static void appMenu(Scanner scanner, Store store, User user) {
        while (true) {
            printUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    store.printProductList();
                    break;
                case 2:
                    System.out.println("Введите ID товара:");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    store.addProductToCart(user, productId);
                    break;
                case 3:
                    System.out.println("Введите ID товара:");
                    int removeProductId = scanner.nextInt();
                    scanner.nextLine();
                    store.removeProductFromCart(user, removeProductId);
                    break;
                case 4:
                    store.printCart(user);
                    break;
                case 5:
                    store.placeOrder(user);
                    break;
                case 6:
                    System.out.println("Выход из аккаунта.");
                    return;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
            }
        }
    }
}
