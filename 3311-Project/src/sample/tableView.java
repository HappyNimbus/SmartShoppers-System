package sample;

public class tableView {

    private String name, price, isle;

    public tableView(String name, String price, String isle) {
        this.name = name;
        this.price = price;
        this.isle = isle;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getIsle() {
        return isle;
    }
}

