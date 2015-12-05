/**
 * Created by kyle on 11/4/15.
 */
public class Wumpus {
    private static Wumpus wumpusInstance;
    private Signals stinky = new Signals("Stinky");

    private Wumpus(){}

    public static synchronized Wumpus getInstance(){
        if(wumpusInstance == null){
            wumpusInstance = new Wumpus();
        }
        return wumpusInstance;
    }

    public Signals getSignal()
    {
        return new Signals(stinky);
    }
}
