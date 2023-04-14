public class Mechanic extends Player{
    private Pipe pipe;
    private Pump pump;

    /**
     * Konstruktor.
     */
    public Mechanic() {
        super();
        this.pipe = null;
        this.pump = null;
    }

    /**
     * A szerelő megjavít egy kilyukasztott csövet.
     *
     * @param p - A megjavítandó cső.
     */
    public void fixPipe(Pipe p){
        p.repair();
    }

    /**
     * A szerelő megjavít egy meghibásodott pumpát.
     *
     * @param p - A megjavítandó pumpa.
     */
    public void fixPump(Pump p) {
        p.repair();
    }

    /**
     * A szerelő felvesz egy csövet.
     */
    public void pickupPipe() {
        if(this.pipe == null) {
            Pipe newPipe = this.position.takePipe();
            if(newPipe != null) {
                setPipe(newPipe);
            }
        }
    }

    /**
     * A szerelő lehelyez egy csövet.
     *
     * @param n - A vég ahova lehelyezi a csövet.
     */
    public void placePipe(FieldNode n) {
        if(this.pipe != null) {
            n.connect(this.pipe);
        }
    }

    public void connectPipe(Pipe p, FieldNode n) {

    }

    public void disconnectPipe(Pipe p, FieldNode n) {

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
