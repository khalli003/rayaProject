package Utils;

import entity.*;

import java.io.*;

public class FileUtils {

    static String[] getPartsOfLine(String line) {
        return line.split(",");
    }


    public static void readUserFile(List<User> listUser, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                User user = new User(partsOfLine[0], partsOfLine[1]);

                listUser.insert(user);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception er) {
                System.out.println("Произошла ошибка");
            }
            throw new RuntimeException("Такой файл не найден");
        }
    }

    public static void readProductFile(List<Product> productList, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);
                Product product = new Product(Integer.parseInt(partsOfLine[0]), partsOfLine[1],Double.parseDouble(partsOfLine[2]));
                productList.insert(product);
            }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception er) {
                System.out.println("Произошла ошибка");
            }
            throw new RuntimeException("Такой файл не найден");
        }
    }

//    public static void readOrderFile(List<Order> ordersList, String fileName, List<Product> productList) {
//        FileReader fileReader = null;
//        BufferedReader bufferedReader = null;
//        try {
//            fileReader = new FileReader(fileName);
//            bufferedReader = new BufferedReader(fileReader);
//
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] partsOfLine = line.split(",");
//                if (partsOfLine.length == 4) {
//                    int orderId = Integer.parseInt(partsOfLine[0]);
//                    String userLogin = partsOfLine[1];
//                    String productString = partsOfLine[2];
//                    double totalPrice = Double.parseDouble(partsOfLine[3]);
//
//                    String[] productIds = productString.split("\\|");
//                    List<Product> productsInOrder = new List<>(new Product[10]);
//
//                    for (String productId : productIds) {
//                        int id = Integer.parseInt(productId);
//                        for (Product product : productList.getAll()) {
//                            if (product.getId() == id) {
//                                productsInOrder.insert(product);
//                                break;
//                            }
//                        }
//                    }
//                    Cart cartInOrder = new Cart();
//
//                    Order order = new Order(orderId, userLogin, cartInOrder);
//                    ordersList.insert(order);
//                } else {
//                    System.out.println("Некорректный формат данных в файле: " + line);
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage());
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//                if (fileReader != null) {
//                    fileReader.close();
//                }
//            } catch (IOException e) {
//                throw new  RuntimeException("Ошибка при закрытии файла: " + e.getMessage());
//            }
//        }
//    }




    public static void writeUserToFile(List<User> listUser, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            User[] users = listUser.getAll();

            for (int i = 0; i < listUser.getSize(); i++) {
                bufferedWriter.write(users[i].getLogin() + "," + users[i].getPassword() + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void writeProductToFile(List<Product> listProduct, String fileName) {
//        FileWriter fileWriter = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            fileWriter = new FileWriter(fileName);
//            bufferedWriter = new BufferedWriter(fileWriter);
//
//            Product[] products = listProduct.getAll();
//
//            for (int i = 0; i < listProduct.getSize(); i++) {
//                bufferedWriter.write(products[i].getId() + "," + products[i].getName() +"," + products[i].getPrice() + "\n");
//            }
//
//            bufferedWriter.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static void writeOrderToFile(List<Order> listOrder, String fileName) {
//        FileWriter fileWriter = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            fileWriter = new FileWriter(fileName);
//            bufferedWriter = new BufferedWriter(fileWriter);
//
//            Order[] orders = listOrder.getAll();
//
//            for (int i = 0; i < listOrder.getSize(); i++) {
//                bufferedWriter.write(orders[i].getOrderId() + "," + orders[i].getUserLogin() + "," +
//                        String.join("|", orders[i].getProductInOrder()) + "," + orders[i].getTotalPrice() + "\n");
//            }
//
//            bufferedWriter.flush();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (bufferedWriter != null) {
//                    bufferedWriter.close();
//                }
//                if (fileWriter != null) {
//                    fileWriter.close();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

}
