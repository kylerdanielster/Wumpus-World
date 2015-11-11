/**
 * Created by kyle on 11/11/15.
 */
public abstract class ItemDecorator implements Item{

    protected Item tempItem;

    public ItemDecorator(Item newItem)
    {
        tempItem = newItem;
    }

    public String getDescription(){
        return tempItem.getDescription();
    }

    public int getWeight(){
        return tempItem.getWeight();
    }

    public int getScore(){
        return tempItem.getScore();
    }

}
