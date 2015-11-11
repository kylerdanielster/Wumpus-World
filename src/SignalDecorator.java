/**
 * Created by kyle on 11/11/15.
 */
public abstract class SignalDecorator implements Signals {

    protected Signals tempSignals;

    public SignalDecorator(Signals newSignal)
    {
        tempSignals = newSignal;
    }

    public String getSignal()
    {
        return tempSignals.getSignal();
    }
}
