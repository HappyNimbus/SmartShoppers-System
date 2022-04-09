package sample;

public class cartView {
    private String name, isle, price;

    public cartView(String name, String isle, String price){
        this.name = name;
        this.isle = isle;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getIsle() {
        return isle;
    }

    public String getPrice() {
        return price;
    }
}
