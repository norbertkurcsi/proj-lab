package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Field;
import proto.Mechanic;
import proto.Pipe;
import proto.Player;

public class FixPipeButton extends ActionButton {
    public FixPipeButton() {
        super();

        this.setText("FIX PIPE");
        this.setEnabled(false);
        this.setVisible(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.fixPipe();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        if (Controller.instance.selectedFields.size() != 0)
            return false;

        Field position = player.getPosition();
        return position instanceof Pipe && ((Pipe)position).isBroken();
    }
}
