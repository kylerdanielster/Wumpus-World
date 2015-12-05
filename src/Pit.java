/**
 * Created by kyle on 12/2/15.
 */
public class Pit {
    private Signals breezy;
    public Pit()
    {
        breezy = new Signals("breeze");
    }

    public Pit(Pit pit)
    {
        this.breezy = pit.breezy;
    }

    public Pit clone()
    {
        return new Pit(this);
    }
    public Signals getSignal()
    {
        return breezy;
    }
}
