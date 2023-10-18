import Utils.FileUtils;
import Utils.Menu;
import entity.List;
import entity.Store;
import entity.User;
import entity.UserStore;
import service.Login;

import java.util.Scanner;

public class Application {

    public final List<User> users;
    public final Login login;

    public Application() {
        users = FileUtils.<User>readFile("src/users.csv");
        login = new Login(users);
    }

    public static void runApplication(){
        Application application = new Application();
        UserStore userStore = new UserStore();
        User user;
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        System.out.println("Добро пожаловать в магазин спорт товаров!");
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Вход");
            System.out.println("2. Регистрация");
            System.out.println("3. Выйти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user = application.login.tryToAuthenticateUser();
                    break;
                case 2:
                    Login.tryToRegisterUser();
                    break;
                case 3:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
            }
        }
    }

    public static void userMenu(Scanner scanner, Store store, User user) {
        while (true) {
            Menu.printMenu();

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