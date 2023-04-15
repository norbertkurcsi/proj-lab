/**
 * A forrásokért felelős osztály
 * A rendszert, pontosabban a belőle kivezető csöveket látja el vízzel
 */
public class Spring extends FieldNode implements Tickable{
    private static int MAX_FLOW = Integer.MAX_VALUE;

    /**
     * Konstruktor
     */
    public Spring(){
        Skeleton.callFunction(this, "create", null);
        Skeleton.endFunction();
    }
    /**
     * Egy időegység elteltét jelenti.
     * A forráshoz kapcsolt csövekbe a lehető legtöbb vizet folyatja a forrásból.
     */
    @Override
    public void tick() {
        Skeleton.callFunction(this, "tick", null);
        for (Pipe pipe : pipes){
            pipe.flow(MAX_FLOW);
        }
        Skeleton.endFunction();
    }
}


