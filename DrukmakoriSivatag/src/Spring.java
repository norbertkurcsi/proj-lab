public class Spring extends FieldNode implements Tickable{
    private static int MAX_FLOW = Integer.MAX_VALUE;

    @Override
    public void tick() {
        Skeleton.callFunction(this, "tick", null);
        for (Pipe pipe : pipes){
            pipe.flow(MAX_FLOW);
        }
        Skeleton.endFunction();
    }
}


