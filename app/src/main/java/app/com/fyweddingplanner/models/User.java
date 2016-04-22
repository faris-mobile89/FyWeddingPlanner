package app.com.fyweddingplanner.models;

/**
 * Created by USER on 2/23/2016.
 */
public class User
{

    public String name;
    public String pass;


    @Override
    public String toString() {
        return name + "--" + pass;
    }
}
