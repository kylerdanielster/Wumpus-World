import java.util.ArrayList;

/**
 * Created by kyle on 11/4/15.
 */
public class Robot {

    private int weightLimit = 50;
    private int score = 0;
    private int lives = 2;
    private int arrow = 1;
    private Item items = null;

    private static Robot robotInstance;

    private Robot(){}

    public static synchronized Robot getInstance(){
        if(robotInstance == null){
            robotInstance = new Robot();
        }
        return robotInstance;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getArrow() {
        return arrow;
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public void showItems()
    {
        if(items != null) {
            System.out.println(items.getDescription());
        } else {
            System.out.println("None");
        }
    }

    public Item dropItem()
    {
        return items;
    }

    public boolean hasItems()
    {
        if(items == null)
            return false;
        else
            return true;
    }



    public void addItem(Item i)
    {
        items = i;
    }
}
