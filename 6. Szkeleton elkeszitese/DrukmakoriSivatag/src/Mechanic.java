public class Mechanic extends Player{
    private Pipe pipe;
    private Pump pump;


    public void fixPipe(Pipe p){
        p.repair();
    }

    public void fixPump(Pump p) {
        p.repair();
    }

    public void pickupPipe() {
        if(this.pipe == null) {
            Pipe newPipe = this.position.takePipe();
            if(newPipe != null) {
                setPipe(newPipe);
            }
        }
    }

    public void placePipe(FieldNode n) {

        boolean hasPipe = Skeleton.yesNoQuestion("Does he have a pipe?");
        if (hasPipe) {
            n.connect(pipe);
            pipe.connect(n);
        }
    }/**
     * Csatlakoztatja a paraméterként kapott csövet, a szintén paraméterként kapott FieldNode csomóponthoz.
     *
     * @param p Csatlakoztatni kívánt cső.
     * @param n Csomópont, ahova a csövet csatlakoztatjuk.
     */
    public void connectPipe(Pipe p, FieldNode n) {
        if (pipe.connect(n)) {
            n.connect(p);
        }
    }

    /**
     * A paraméterként kapott FieldNode csomópont és a szintén paraméterként kapott cső közötti kapcsolatot megszünteti.
     *
     * @param p Lecsatlakoztatni kívánt cső.
     * @param n Csomópont, ahonnan a csövet lecsatlakoztatjuk.
     */
    public void disconnectPipe(Pipe p, FieldNode n) {
        if (p.disconnect(n)) {
            n.disconnect(p);
        }
    }

    /**
     * A szerelő felvesz egy pumpát ha van a mezőn, valamint előzőleg nem rendelkezett pumpával.
     * Ez a pumpa a szerelő eszköztárába kerül.
     */
    public void pickupPump() {
        boolean hasPump = Skeleton.yesNoQuestion("Does he have a pump?");
        if (hasPump == true) {
            return;
        }
        Pump newPump = position.takePump();
        if (newPump == null) {
            return;
        }
        setPump(newPump);
    }

    /**
     * A szerelő letesz egy pumpát, amely az eszköztárában volt eddig, amennyiben előzőleg rendelkezett pumpával.
     * A csőre letett pumpa a csövet kettéválasztja, egy új cső létrejön és a megfelelő végeit a régi és új csőnek csatlakoztatja a pumpához.
     * Ezután az eszköztára kiürül és felvehet egy más pumpát az elkövetkezendő körökben.
     *
     * @param pipe Ide helyezi a pumpát. Ez vágódik ketté.
     */
    public void placePump(Pipe pipe) {
        Pipe newPipe = pipe.cut();
        if (newPipe == null) {
            return;
        }
        pipe.connect(pump);
        newPipe.connect(pump);
        pump.connect(pipe);
        pump.connect(newPipe);
        setPump(null);
    }

    /**
     * Beállítja a paraméterként kapott pumpát a szerelő pumpájává.
     *
     * @param pump Az új pumpa
     */
    public void setPump(Pump pump) {
        this.pump = pump;
    }

    /**
     * Beállítja a paraméterként kapott csövet a szerelő csöveként.
     * @param pipe Az új cső
     */
    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }
}