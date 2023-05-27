package GUI.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import GUI.Controller;
import proto.Field;
import proto.Pipe;
import proto.Player;
import proto.Pump;

public class ChangeFlowButton extends ActionButton {
    public ChangeFlowButton() {
        super();

        this.setText("CHANGE FLOW");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.changeFlow();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (player == null || !(player.getPosition() instanceof Pump))
            return false;

        List<Field> selectedFields = Controller.instance.selectedFields;
        if (selectedFields.size() != 2)
            return false;

        if(!(selectedFields.get(0) instanceof Pipe) || !(selectedFields.get(1) instanceof Pipe))
            return false;

        return (player.getPosition()).hasNeighbour(selectedFields.get(0)) && (player.getPosition()).hasNeighbour(selectedFields.get(1));
    }
}
