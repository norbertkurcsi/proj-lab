package GUI.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import GUI.Controller;
import proto.Field;
import proto.Player;

public class MoveButton extends ActionButton {
    public MoveButton() {
        super();

        this.setText("MOVE");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.movePlayer();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (player == null)
            return false;

        List<Field> selectedFields = Controller.instance.selectedFields;
        if (selectedFields.size() != 1)
            return false;

        // Check if it is a neighbouring field
        return player.getPosition().hasNeighbour(selectedFields.get(0));
    }
}
