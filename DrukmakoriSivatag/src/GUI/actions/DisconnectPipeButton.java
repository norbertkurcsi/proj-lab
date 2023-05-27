package GUI.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import GUI.Controller;
import proto.*;

public class DisconnectPipeButton extends ActionButton {
    public DisconnectPipeButton() {
        super();

        this.setText("DISCONNECT PIPE");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.disconnectPipe();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        List<Field> selectedFields = Controller.instance.selectedFields;
        if (selectedFields.size() != 1)
            return false;

        Field from = selectedFields.get(0);
        if (!(from instanceof FieldNode)) {
            return false;
        }

        return (player.getPosition() instanceof Pipe) && from.hasNeighbour(player.getPosition()) && ((Pipe)player.getPosition()).isEmpty();
    }
}
