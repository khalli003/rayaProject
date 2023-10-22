package entity;

public class  Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static Product searchProductByName(List<Product> productList, String productName) {
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID товара: " + id + ", Название: " + name + ", Цена: " + price;
    }

}
