package proto;

/**
 * A Saboteur osztálya.
 */
public class Saboteur extends Player {

    /**
     * Konstruktor.
     */
    public Saboteur() {
    }

    public void makeSlippery(Pipe p) {
        p.makeSlippery();
    }

    @Override
    public String toString() {

        return "Saboteur " +
                Proto.findName(this) +
                " on " +
                Proto.findName(this.position);
    }
}
