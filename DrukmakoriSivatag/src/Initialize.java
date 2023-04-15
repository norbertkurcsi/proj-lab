public class Initialize {
    public static void initConnectPipe() {
        Pipe pipe = new Pipe();
        Skeleton.names.put(pipe, "pipe");
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "mechanic");
        Pump pump = new Pump();
        Skeleton.names.put(pump, "pump");

        m.connectPipe(pipe, pump);

        Skeleton.names.clear();
    }

    public static void initDisconnectPipe() {
        Pipe pipe = new Pipe();
        Skeleton.names.put(pipe, "pipe");
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Pump pump = new Pump();
        Skeleton.names.put(pump, "pump");
        Cistern c = new Cistern();
        Skeleton.names.put(c, "c");

        pipe.connect(pump);
        pipe.connect(c);
        pump.connect(pipe);
        c.connect(pipe);

        m.disconnectPipe(pipe, pump);

        Skeleton.names.clear();
    }

    public static void initFixPipe() {
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Pipe p = new Pipe();
        Skeleton.names.put(p, "p");

        m.moveTo(p);
        p.addPlayer(m);

        m.fixPipe(p);

        Skeleton.names.clear();
    }

    public static void initPlacePipe() {
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Pipe p = new Pipe();
        Skeleton.names.put(p, "p");
        Pump pump = new Pump();
        Skeleton.names.put(pump, "pump");

        m.moveTo(p);
        pump.addPlayer(m);

        m.placePipe(pump);
    }

    static public void initPickupPipe() {
        Mechanic mechanic = new Mechanic();
        Skeleton.names.put(mechanic, "mechanic");
        Cistern cistern = new Cistern();
        Skeleton.names.put(cistern, "cistern");

        mechanic.moveTo(cistern);
        cistern.tick();
        mechanic.pickupPipe();

        Skeleton.names.clear();
    }

    public static void initPuncturePipe() {
        Saboteur s = new Saboteur();
        Skeleton.names.put(s, "s");
        Pipe pipe = new Pipe();
        Skeleton.names.put(pipe, "pipe");

        s.moveTo(pipe);
        pipe.addPlayer(s);

        s.breakPipe(pipe);

        Skeleton.names.clear();
    }

    ///////////////////////////////////////////////////////////////////

    public static void initFixPump() {
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Pump p = new Pump();
        Skeleton.names.put(p, "p");

        m.moveTo(p);
        p.addPlayer(m);

        m.fixPump(p);

        Skeleton.names.clear();
    }

    public static void initPlacePump() {
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Pump p = new Pump();
        Skeleton.names.put(p, "p");
        Pipe pipe = new Pipe();
        Skeleton.names.put(pipe, "pipe");

        m.setPump(p);
        m.moveTo(pipe);
        pipe.addPlayer(m);

        m.placePump(pipe);

        Skeleton.names.clear();
    }

    public static void initMechanicSetsPump() {
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Pump pump = new Pump();
        Skeleton.names.put(pump, "pump");
        Pipe to = new Pipe();
        Skeleton.names.put(to, "to");
        Pipe from = new Pipe();
        Skeleton.names.put(from, "from");

        m.moveTo(pump);
        pump.addPlayer(m);

        m.setPumpDirection(pump, from, to);

        Skeleton.names.clear();
    }

    public static void initSaboteurSetsPump() {
        Saboteur s = new Saboteur();
        Skeleton.names.put(s, "s");
        Pump pump = new Pump();
        Skeleton.names.put(pump, "pump");
        Pipe to = new Pipe();
        Skeleton.names.put(to, "to");
        Pipe from = new Pipe();
        Skeleton.names.put(from, "from");

        s.moveTo(pump);
        pump.addPlayer(s);

        s.setPumpDirection(pump, from, to);

        Skeleton.names.clear();
    }

    public static void initTakePump() {
        Mechanic m = new Mechanic();
        Skeleton.names.put(m, "m");
        Cistern c = new Cistern();
        Skeleton.names.put(c, "c");

        m.moveTo(c);
        c.addPlayer(m);

        m.pickupPump();

        Skeleton.names.clear();
    }

    static public void initMoveToPipeFromPump() {
        Mechanic mechanic = new Mechanic();
        Skeleton.names.put(mechanic, "mechanic");
        Pump pos = new Pump();
        Skeleton.names.put(pos, "pos");
        Pipe dest = new Pipe();
        Skeleton.names.put(dest, "dest");

        mechanic.moveTo(pos);
        dest.connect(pos);
        pos.connect(dest);

        Skeleton.names.clear();
    }

    static public void initMovetoPumpFromPipe() {
        Mechanic mechanic = new Mechanic();
        Skeleton.names.put(mechanic, "mechanic");
        Pipe pos = new Pipe();
        Skeleton.names.put(pos, "pos");
        Pump dest = new Pump();
        Skeleton.names.put(dest, "dest");

        mechanic.moveTo(pos);
        dest.connect(pos);
        pos.connect(dest);

        Skeleton.names.clear();
    }

    static public void initMoveToCisternFromPipe() {
        Mechanic mechanic = new Mechanic();
        Skeleton.names.put(mechanic, "mechanic");
        Pipe pos = new Pipe();
        Skeleton.names.put(pos, "pos");
        Cistern dest = new Cistern();
        Skeleton.names.put(dest, "dest");

        mechanic.moveTo(pos);
        dest.connect(pos);
        pos.connect(dest);

        Skeleton.names.clear();
    }

    static public void initMoveToSpringFromPipe() {
        Mechanic mechanic = new Mechanic();
        Skeleton.names.put(mechanic, "mechanic");
        Pipe pos = new Pipe();
        Skeleton.names.put(pos, "pos");
        Spring dest = new Spring();
        Skeleton.names.put(dest, "dest");

        mechanic.moveTo(pos);
        dest.connect(pos);
        pos.connect(dest);

        Skeleton.names.clear();
    }

//    static public void initNetworkBranchFilledUp() {
//        Spring spring = new Spring();
//        Skeleton.names.put(spring, "spring");
//        Pipe pipe1 = new Pipe();
//        Skeleton.names.put(pipe1, "pipe1");
//        pipe1.connect(spring);
//        spring.connect(pipe1);
//
//        Pump pump1 = new Pump();
//        Skeleton.names.put(pump1, "pump1");
//        pipe1.connect(pump1);
//        pump1.connect(pipe1);
//        Pipe pipe2 = new Pipe();
//        Skeleton.names.put(pipe2, "pipe2");
//        pipe2.connect(pump1);
//        pump1.connect(pipe2);
//
//        Pump pump2 = new Pump();
//        Skeleton.names.put(pump2, "pump2");
//        pipe2.connect(pump2);
//        pump2.connect(pipe2);
//
//        pump2.addVolume();
//        pump1.addVolume();
//
//        spring.tick();
//        pump1.tick();
//        //pipe2.setWaterVolume
//        //pipe1.setWaterVolume
//
//        spring.tick();
//        pump1.tick();
//        pump2.tick();
//
//        Skeleton.names.clear();
//    }
//
//    static public void initPumpingIntoBrokenPump() {
//        Spring spring = new Spring();
//        Skeleton.names.put(spring, "spring");
//        Pipe pipe1 = new Pipe();
//        Skeleton.names.put(pipe1, "pipe1");
//        pipe1.connect(spring);
//        spring.connect(pipe1);
//
//        Pump pump1 = new Pump();
//        Skeleton.names.put(pump1, "pump1");
//        pipe1.connect(pump1);
//        pump1.connect(pipe1);
//        Pipe pipe2 = new Pipe();
//        Skeleton.names.put(pipe2, "pipe2");
//        pipe2.connect(pump1);
//        pump1.connect(pipe2);
//        Pump pump2 = new Pump();
//        Skeleton.names.put(pump2, "pump2");
//        pipe2.connect(pump2);
//        pump2.connect(pipe2);
//
//        pump2.breakPump();
//        Skeleton.names.clear();
//    }
}
