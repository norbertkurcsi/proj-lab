package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Field;
import proto.Mechanic;
import proto.Pipe;
import proto.Player;

public class BreakPipeButton extends ActionButton {
    public BreakPipeButton() {
        super();

        this.setEnabled(false);
        this.setText("BREAK PIPE");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.breakPipe();
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
        return position instanceof Pipe && !((Pipe) position).isBroken();
    }
}
