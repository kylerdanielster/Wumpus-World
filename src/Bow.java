/**
 * Created by kyle on 12/2/15.
 */
public class Bow extends Item {
    public Bow()
    {
        super();
    }

    public Bow(Bow bow)
    {
        super();
    }

    public Bow clone()
    {
        return new Bow(this);
    }
}
