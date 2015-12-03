/**
 * Created by kyle on 12/2/15.
 */
public class Cookie extends Item{
    Signals aroma;

    public Cookie()
    {
        super();
        aroma = new Signals("Something smells good");

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

}
