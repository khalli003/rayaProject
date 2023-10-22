package entity;

public class Cart {
    private List<Product> products = new List<>(new Product[10]);

    public Cart() {
        this.products = getProducts();
    }

    public void addProduct(Product product) {
        products.insert(product);
    }

    public static void addProductToCart(int productId, Cart cart, List<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                cart.addProduct(product);
                System.out.println("Товар: " + product.getName() + " добавлен в корзину.");
                return;
            }
        }
        System.out.println("Товар с указанным ID не найден.");
    }

    public void removeProductFromCart(int removeProductId,Cart cart) {
                for (int i = 0; i < cart.getProducts().getSize(); i++) {
                    Product product = cart.getProducts().getAll()[i];
                    if (product.getId() == removeProductId) {
                        cart.removeProduct(product);
                        System.out.println("Товар удален из корзины.");
                        return;
                    }
                }
            }

    public void printCart(Cart cart) {
        if (cart != null) {
            System.out.println("Товары в корзине:");
            for (int j = 0; j < cart.getProducts().getSize(); j++) {
                Product product = cart.getProducts().getAll()[j];
                System.out.println(product);
            }
        }else {
                System.out.println("У вас нет товаров в корзине");
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
