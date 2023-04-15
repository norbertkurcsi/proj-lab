public class Initialize {
    static public void initPickupPipe() {
        Mechanic mechanic = new Mechanic();
        Cistern cistern  = new Cistern();

        mechanic.moveTo(cistern);
        cistern.tick();

        mechanic.pickupPipe();
    }
}
