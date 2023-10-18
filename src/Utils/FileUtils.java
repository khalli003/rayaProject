package Utils;

import entity.List;
import entity.User;

import java.io.*;

public class FileUtils {


    public static <T> List<T> readFile(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName); ObjectInputStream objectIn =
                new ObjectInputStream(fileIn)) {
            Object object = objectIn.readObject();
            return (List<T>) object;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {

            var file = new File(fileName);
            try {
                boolean exists = file.exists();

                if (!exists) {
                    var newFile = file.createNewFile();

                    if (!newFile) {
                        System.out.println("Что-то пошло не так при создании файла");
                        throw new RuntimeException("умер");
                    } else {
                        if (fileName.contains("users")) {
                            var users = new List<User>(new User[10]);
                            users.insert(new User(
                                    "1ommy",
                                    "qwerty"
                            ));


                            fileWriter(users, fileName);

                            return readFile(fileName);
                        }
                    }
                }

            } catch (IOException exc) {
                System.out.println(exc.getMessage());
            }
        }
        return null;
    }

    public static <T extends Serializable> void fileWriter (List<T> list, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}