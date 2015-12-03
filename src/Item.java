import java.util.Objects;

/**
 * Created by kyle on 11/4/15.
 */
public abstract class Item {

    String description;
    int weight;
    int score;

    public Item(){}

    public Item(Item i)
    {
        this.description = i.description;
        this.weight = i.weight;
        this.score = i.score;
    }

    public abstract Item clone();

    public String getDescription()
    {
        return description;
    }

    public int getWeight()
    {
        return weight;
    }

    public int getScore()
    {
        return score;
    }

}
