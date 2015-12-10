/**
 * Created by kyle on 12/2/15.
 */
public class Cookie extends Item{
    private String signal;

    public Cookie()
    {
        super();
        signal = "smelling good";
        setDescription("cookie");
    }

    public Cookie(Cookie cookie)
    {
        super(cookie);
        this.signal = cookie.signal;
    }

    public Cookie clone()
    {
        return new Cookie(this);
    }

    public String getSignal()
    {
        return signal;
    }

}
