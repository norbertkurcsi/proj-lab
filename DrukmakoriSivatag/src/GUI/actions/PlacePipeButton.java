package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.FieldNode;
import proto.Mechanic;
import proto.Player;

public class PlacePipeButton extends ActionButton {
    public PlacePipeButton() {
        super();

        this.setText("PLACE PIPE");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.placePipe();
        });
    }

    public boolean canPerform() {
        if (Controller.instance == null)
            return false;

        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        if(Controller.instance.selectedFields.size() != 0
                || !(player.getPosition() instanceof FieldNode))
            return false;
        return ((Mechanic)player).getPipe() != null;
    }
}
