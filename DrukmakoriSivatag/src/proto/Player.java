package proto;

/**
 * A Player absztrakt osztálya.
 */
public abstract class Player {
    /**
     * A játékos pozícióját jelölő mező.
     */
    protected Field position;

    /**
     * A játékos pozíciót vált.
     *
     * @param f A játékos célpozíciója.
     */
    public void moveTo(Field f) {
        boolean canMove = position.removePlayer(this);
        if (!canMove) {
            return;
        }

        Field newPosition = f.addPlayer(this);
        if (newPosition == null) {
            position.addPlayer(this); // Nem tudtunk átlépni úgyhogy helyben maradunk
            return;
        }

        setPosition(f); // Sikeresen átléptünk
    }

    /**
     * A játékos a paraméterként kapott pumpát átállítja, hogy melyik
     * csőből melyikbe pumpálja a vizet.
     *
     * @param pump Az átállítandó pumpa.
     * @param from Ebből a csőből fogja pumpálni a pumpa a vizet.
     * @param to   Ebbe a csőbe fogja pumpálni a pumpa a vizet.
     */
    public void setPumpDirection(Pump pump, Pipe from, Pipe to) {
        pump.changeFlow(from, to);
    }

    /**
     * Beállítja a játékos pozícióját a paramétrként megadott mezőre.
     *
     * @param f A játékos új pozicíója.
     */
    public void setPosition(Field f) {
        position = f;
    }

    // TODO: Dokumentálni kell majd
    public void BreakPipe(Pipe p) {
        p.breakPipe();
    }

    // TODO: Dokumentálni kell majd
    public void MakeSticky(Pipe p) {
        p.makeSticky();
    }
}
