package service;
import Utils.FileUtils;
import entity.List;
import entity.User;

import java.util.Scanner;

public class Login {
    private static List<User> listUser;
    static Scanner scanner = new Scanner(System.in);

    static String userLogin;
    static String password;

    public Login(List<User> listUser) {
        this.listUser = listUser;
    }

    public static User authenticate(String login, String password) {
        for (User user : listUser.getAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    public static void registerUser(User user, List<User> users) {
        users.insert(user);
        FileUtils.fileWriter(listUser,"src/user.scv");
    }

    public static void tryToRegisterUser() {
        String passwordCopy;
        boolean workFunctionsRegistration = true;
        while (workFunctionsRegistration) {
            System.out.println("Создайте логин");
            userLogin = scanner.nextLine();
            System.out.println("Создайте пароль");
            password = scanner.nextLine();
            System.out.println("Повторите пароль");
            passwordCopy = scanner.nextLine();

            if (passwordCopy.equals(password)) {
                User user = new User(userLogin, password);
                registerUser(user, listUser);
                System.out.println("Спасибо, что выбираете нас, регистрация прошла успешно");
                workFunctionsRegistration = false;
                tryToAuthenticateUser();
            } else {
                System.out.println("Пароли не совпадают. Повторите попытку.");
            }
        }
    }
    public static User tryToAuthenticateUser () {
        System.out.print("Логин: ");
        userLogin = scanner.nextLine();
        System.out.print("Пароль: ");
        password = scanner.nextLine();

        User authenticatedUser = authenticate(userLogin, password);

        if (authenticatedUser == null) {
            System.out.println("Ошибка аутентификации. Неверный логин или пароль.");
            System.exit(1);
        }
        return authenticatedUser;
    }
}