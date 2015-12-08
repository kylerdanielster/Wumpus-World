/**
 * Created by kyle on 12/2/15.
 */
public class Gold extends Item {
    private Signals glitters;

    public Gold() {
        super();
        glitters = new Signals("Something is glittering.");
        description = "gold";

    }


    public Gold(Gold gold) {
        super();
        this.glitters = gold.glitters;

    }

    public Gold clone() {
        return new Gold(this);
    }

    public Signals getSignal()
    {
        return new Signals(glitters);
    }

}
