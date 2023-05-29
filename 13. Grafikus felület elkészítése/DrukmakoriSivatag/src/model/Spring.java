package model;

/**
 * A forrásokért felelős osztály
 * A rendszert, pontosabban a belőle kivezető csöveket látja el vízzel
 */
public class Spring extends FieldNode implements Tickable {
    /**
     * Konstruktor
     */
    public Spring() {
    }

    /**
     * Egy időegység elteltét jelenti.
     * A forráshoz kapcsolt csövekbe a lehető legtöbb vizet folyatja a forrásból.
     */
    @Override
    public void tick() {
        for (Pipe pipe : pipes) {
            pipe.flow(Integer.MAX_VALUE);
        }
    }

    @Override
    public boolean hasNeighbour(Field field) {
        return pipes.contains(field);
    }

}
