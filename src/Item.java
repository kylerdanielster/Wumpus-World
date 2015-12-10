import java.util.Objects;

/**
 * Created by kyle on 11/4/15.
 */
public abstract class Item {

    private String signal;
    private String description;
    private int weight;
    private int score;

    public Item(){}

    public Item(Item i)
    {
        this.signal = i.signal;
        this.description = i.description;
        this.weight = i.weight;
        this.score = i.score;
    }

    public abstract Item clone();

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public int getWeight()
    {
        return weight;
    }

    public int getScore()
    {
        return score;
    }

    public abstract String getSignal();



}
