package Utils;

public class Menu {
    public static void printMenu(){
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
}
