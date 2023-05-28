package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Cistern;
import proto.Mechanic;
import proto.Player;

public class PickupPipeButton extends ActionButton {
    public PickupPipeButton() {
        super();

        this.setContentAreaFilled(false);
        this.setRolloverEnabled(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setText("PICKUP PIPE");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.pickupPipe();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        if (Controller.instance.selectedFields.size() != 0
                || !(player.getPosition() instanceof Cistern))
            return false;
        return ((Cistern)player.getPosition()).isPipeAvailable() && ((Mechanic) player).getPipe() == null;
    }
}
