public class Mechanic extends Player{
    public void fixPipe(Pipe p){
        System.out.print("Meghivtak ezt is apjae!");
    }

    public void fixPump(Pump p) {
        p.repair();
    }

    public void pickupPipe(Node n) {

    }

    public void placePipe(Node n) {

    }

    public void connectPipe(Pipe p, Node n) {

    }

    public void disconnectPipe(Pipe p, Node n) {

    }

    public void pickupPump() {

    }

    public void placePump(Pump p, Pipe pipe) {

    }

    public void setPump(Pump p) {

    }
}
