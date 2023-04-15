public class Saboteur extends Player {

    /**
     * A szabotőr egy paraméterként kapott csövet kilyukaszt, amennyiben jelenleg a csövön áll.
     *
     * @param p A kilyukasztandó cső.
     */
    public void breakPipe(Pipe p) {
        Skeleton.callFunction(this, "breakPipe", new Object[]{p});
        boolean standingThere = Skeleton.yesNoQuestion("Is he standing on the pipe?");
        if (standingThere) {
            p.breakPipe();
        }
        Skeleton.endFunction();
    }
}
