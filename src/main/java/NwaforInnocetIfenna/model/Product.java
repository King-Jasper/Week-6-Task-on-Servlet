package NwaforInnocetIfenna.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;

    private String image;

    public Product(String name, String category, int quantity, double price, String image) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public Product(int id, String name, String category, int quantity, double price, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




    public double getPrice() {
        return price;
    }
}
