/**
 * Created by kyle on 12/2/15.
 */
public class Gold extends Item {
    private String signal;


    public Gold() {
        super();
        signal = "glittering.";
        setDescription("gold");

    }

    public Gold(Gold gold) {
        super();
        this.signal = gold.signal;
    }

    public Gold clone() {
        return new Gold(this);
    }

    public String getSignal()
    {
        return signal;
    }

}
