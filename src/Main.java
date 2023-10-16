
import entity.Store;
import entity.User;
import entity.UserStore;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        UserStore userStore = new UserStore();
        User newUser = new User("login","password" );
        userStore.registerUser(newUser);
        userStore.loadUserList();
        userStore.saveUserList();



        Scanner scanner = new Scanner(System.in);
                Store store = new Store();
                User user = null;

                System.out.println("Добро пожаловать в магазин спорт товаров!");

                while (true) {
                    System.out.println("\nВыберите действие:");
                    System.out.println("1. Вход");
                    System.out.println("2. Регистрация");
                    System.out.println("3. Выйти");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            user = store.login();
                            if (user != null) {
                                System.out.println("Вход выполнен!");
                                userMenu(scanner, store, user);
                            } else {
                                System.out.println("Ошибка входа, попробуйте снова.");
                            }
                            break;
                        case 2:
                            user = store.register();
                            if (user != null) {
                                System.out.println("Регистрация выполнена!");
                                userMenu(scanner, store, user);
                            } else {
                                System.out.println("Ошибка регистрации, попробуйте снова.");
                            }
                            break;
                        case 3:
                            System.out.println("До свидания!");
                            System.exit(0);
                        default:
                            System.out.println("Неправильный выбор, попробуйте снова.");
                    }
                }
            }

            public static void userMenu(Scanner scanner, Store store, User user) {
                while (true) {
                    System.out.println("\nВыберите действие:");
                    System.out.println("1. Посмотреть список товаров");
                    System.out.println("2. Добавить товар в корзину");
                    System.out.println("3. Удалить товар из корзины");
                    System.out.println("4. Посмотреть корзину");
                    System.out.println("5. Сделать заказ");
                    System.out.println("6. Выйти");

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


