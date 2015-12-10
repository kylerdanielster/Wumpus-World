/**
 * Created by kyle on 12/2/15.
 */
public class Pit {
    private String signal;
    public Pit()
    {
        signal = "breezy";
    }

    public Pit(Pit pit)
    {
        this.signal = pit.signal;
    }

    public Pit clone()
    {
        return new Pit(this);
    }

    public String getSignal()
    {
        return signal;
    }
}
