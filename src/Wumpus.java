/**
 * Created by kyle on 11/4/15.
 */
public class Wumpus {
    private static Wumpus wumpusInstance;
    private String signal = "stinky";

    private Wumpus(){}

    public static synchronized Wumpus getInstance(){
        if(wumpusInstance == null){
            wumpusInstance = new Wumpus();
        }
        return wumpusInstance;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }
}
