package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Field;
import proto.Mechanic;
import proto.Player;
import proto.Pump;

public class FixPumpButton extends ActionButton {
    public FixPumpButton() {
        super();

        this.setText("FIX PUMP");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.fixPump();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (player == null || !(player instanceof Mechanic))
            return false;

        if (Controller.instance.selectedFields.size() != 0)
            return false;

        Field position = player.getPosition();
        return position instanceof Pump && ((Pump) position).isBroken();
    }
}
