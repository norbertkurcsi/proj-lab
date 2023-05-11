package proto;

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
    public String toString() {
        String playerList = "";
        if (players.isEmpty()) playerList = "null";
        else {
            for (Player p : players) {
                playerList += (Proto.findName(p) + ", ");
            }
            playerList = playerList.substring(0, playerList.length() - 2);
        }

        return "Spring " +
                Proto.findName(this) +
                " with ends: " +
                super.toString() +
                " standing players: " +
                playerList;
    }
}
