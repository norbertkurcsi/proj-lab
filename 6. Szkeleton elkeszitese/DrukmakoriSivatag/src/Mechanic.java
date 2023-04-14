public class Mechanic extends Player{
    private Pipe pipe;
    private Pump pump;


    public void fixPipe(Pipe p){
        p.repair();
    }

    public void fixPump(Pump p) {
        p.repair();
    }

    public void pickupPipe() {
        if(this.pipe == null) {
            Pipe newPipe = this.position.takePipe();
            if(newPipe != null) {
                setPipe(newPipe);
            }
        }
    }

    public void placePipe(Node n) {
        if(this.pipe != null) {
            n.connect(this.pipe);

        }
    }

    public void connectPipe(Pipe p, Node n) {

    }

    public void disconnectPipe(Pipe p, Node n) {

    }

    public void pickupPump() {

    }

    public void placePump(Pump p, Pipe pipe) {

    }

    public void setPump(Pump pump) {
        this.pump = pump;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }
}
