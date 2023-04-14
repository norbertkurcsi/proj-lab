public class Spring extends Field implements Tickable, Node{
    private static int MAX_FLOW = Integer.MAX_VALUE;

    public boolean addPlayer(Player p){
        return false;
    }

    public String GetClass(){
        return "Spring";
    }

    @Override
    public void connect(Pipe p) {

    }

    @Override
    public void disconnect(Pipe p) {

    }

    @Override
    public void tick() {

    }
}


