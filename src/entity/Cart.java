package entity;

import java.util.ArrayList;

class Cart {
    private entity.List<Product> products;

    public Cart() {
        this.products = new Product<>();
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

    public void clear() {
        products.clear();
    }
}
