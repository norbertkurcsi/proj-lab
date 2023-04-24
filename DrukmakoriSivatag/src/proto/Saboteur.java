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
}
