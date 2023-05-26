package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Cistern;
import proto.Mechanic;
import proto.Player;

public class PickupPipeButton extends ActionButton {
    public PickupPipeButton() {
        super();

        this.setText("PICKUP PIPE");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.pickupPipe();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (player == null || !(player instanceof Mechanic))
            return false;

        return Controller.instance.selectedFields.size() == 0
                && player.getPosition() instanceof Cistern;
    }
}
