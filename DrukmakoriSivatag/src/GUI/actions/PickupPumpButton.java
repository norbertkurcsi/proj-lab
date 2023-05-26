package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Cistern;
import proto.Mechanic;
import proto.Player;

public class PickupPumpButton extends ActionButton {
    public PickupPumpButton() {
        super();

        this.setText("PICKUP PUMP");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.pickupPump();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        return Controller.instance.selectedFields.size() == 0
                && player.getPosition() instanceof Cistern && ((Mechanic) player).getPump() == null;
    }
}