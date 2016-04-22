package app.com.fyweddingplanner.models;

/**
 * Created by USER on 4/19/2016.
 */
public class CardsShop {


    public String cardShopName;
    public String offers;
    public String description;
    public String location;


    @Override
    public String toString() {
        return cardShopName + " " + offers+""+description+""+location;
    }
}
