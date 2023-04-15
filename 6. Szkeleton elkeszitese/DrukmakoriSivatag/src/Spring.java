public class Spring extends FieldNode implements Tickable{
    private static int MAX_FLOW = Integer.MAX_VALUE;

    public String GetClass(){
        return "Spring";
    }

    @Override
    public void tick() {
        for (Pipe pipe : pipes){
            pipe.flow(MAX_FLOW);
        }
    }
}


