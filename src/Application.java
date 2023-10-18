import Utils.FileUtils;
import Utils.Menu;
import entity.*;
import service.Login;

import java.util.Scanner;


public class Application {

    static List<User> users = new List<>(new User[10]);

    static List<Cart> carts = new List<>(new Cart[10]);

    public static final Login login = new Login(users);


    public static void runApplication(){
        FileUtils.readUserFile(users,"src/users.csv");
        Application application = new Application();
        UserStore userStore = new UserStore();
        User user = new User("log","pass");
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        System.out.println("Добро пожаловать в магазин спорт товаров!");
        boolean workLoginPage = true;
        while (workLoginPage==true) {
            Menu.printLoginMenu();
            int choice =scanner.nextInt();
            switch (choice) {
                case 1:
                    user = application.login.tryToAuthenticateUser();
                    workLoginPage=false;
                    break;
                case 2:
                    user = Login.tryToRegisterUser();
                    break;
                case 3:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
            }

        }
        if (user!=null) {
            Store store = new Store(users, carts,user);

            Menu.appMenu(scanner, store, user);
        }
    }

}