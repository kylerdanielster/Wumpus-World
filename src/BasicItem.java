/**
 * Created by kyle on 11/10/15.
 */
public class BasicItem implements Item {


    @Override
    public String getDescription() {
        return "Items: ";
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public int getScore() {
        return 0;
    }

}
