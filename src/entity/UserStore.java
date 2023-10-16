package entity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

    public class UserStore {
        private List<User> userList;

        public UserStore() {
            this.userList = new ArrayList<>();
        }

        public void registerUser(User user) {

            userList.add(user);
        }

        public void saveUserList() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/users.dat"))){
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/users.txt"));
                for (User user : userList) {
                    String userData = user.getLogin() + "," + user.getPassword() + "\n";
                    writer.write(userData); // Сохранение в users.txt
                }
                oos.writeObject(userList);
                System.out.println("Список пользователей успешно сохранен в файл.");
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла users.dat: " + e.getMessage());

            }

        }

        public void loadUserList() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/users.dat"))) {
                userList = (List<User>) ois.readObject();
                System.out.println("Список пользователей успешно загружен из файла.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Ошибка при чтении файла users.dat: " + e.getMessage());
            }
        }
    }

