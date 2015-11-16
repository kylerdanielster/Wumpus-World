import java.util.ArrayList;

/**
 * Created by kyle on 11/4/15.
 */
public class Robot {

    private int weightLimit = 0;
    private int score = 0;
    private int lives = 1;
    private ItemDecorator weapon;
    private int arrow = 1;
    private ArrayList <ItemDecorator> items;

    private static Robot robotInstance;

    private Robot(){}

    public static synchronized Robot getInstance(){
        if(robotInstance == null){
            robotInstance = new Robot();
        }
        return robotInstance;
    }
    //TODO: Detect Sensors

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

    public ItemDecorator getWeapon() {

        return weapon; // privacy leak?
    }

    public void setWeapon(ItemDecorator weapon) {
        this.weapon = weapon;
    }

    public ArrayList<ItemDecorator> getItems() {
        return items; // privacy leak?
    }

    public void addItems(ItemDecorator item) {
        items = new ArrayList<ItemDecorator>();
        this.items.add(item);
    }
}
