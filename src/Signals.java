/**
 * Created by kyle on 11/4/15.
 */
public class Signals {

    String signal;
    public Signals(String s)
    {
        signal = s;
    }

    public Signals(Signals signal)
    {
        this.signal = signal.signal;
    }

    public Signals clone()
    {
        return new Signals(this);
    }

    public String getSignal()
    {
        return signal;
    }
}
