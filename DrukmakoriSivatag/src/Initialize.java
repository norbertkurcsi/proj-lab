public class Initialize {
    static public void initPickupPipe() {
        Mechanic mechanic = new Mechanic();
        Skeleton.names.put(mechanic, "mechanic");
        Cistern cistern  = new Cistern();
        Skeleton.names.put(cistern, "cistern");

        mechanic.moveTo(cistern);
        cistern.tick();

        mechanic.pickupPipe();
        Skeleton.names.clear();
    }
}
