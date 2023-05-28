package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Mechanic;
import proto.Pipe;
import proto.Player;

public class PlacePumpButton extends ActionButton {
    public PlacePumpButton() {
        super();

        this.setContentAreaFilled(false);
        this.setRolloverEnabled(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setText("PLACE PUMP");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.placePump();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        return Controller.instance.selectedFields.size() == 0
                && player.getPosition() instanceof Pipe && ((Mechanic) player).getPump() != null
                && ((Pipe) player.getPosition()).isEmpty()
                && !((Pipe) player.getPosition()).isBroken();
    }
}
