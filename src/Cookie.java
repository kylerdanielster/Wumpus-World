/**
 * Created by kyle on 12/2/15.
 */
public class Cookie extends Item{
    private Signals aroma;

    public Cookie()
    {
        super();
        aroma = new Signals("smelling good");
        setDescription("cookie");
    }

    public Cookie(Cookie cookie)
    {
        super();
        this.aroma = cookie.aroma;
    }

    public Cookie clone()
    {
        return new Cookie(this);
    }

    public Signals getSignal()
    {
        return new Signals(aroma);
    }

}
