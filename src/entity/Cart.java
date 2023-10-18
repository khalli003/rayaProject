package entity;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = getProducts();
    }

    public void addProduct(Product product) {
        products.insert(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

}
