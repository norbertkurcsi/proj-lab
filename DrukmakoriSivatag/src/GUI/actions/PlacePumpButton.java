package GUI.actions;

import java.awt.event.ActionEvent;

import GUI.Controller;
import proto.Mechanic;
import proto.Pipe;
import proto.Player;

public class PlacePumpButton extends ActionButton {
    public PlacePumpButton() {
        super();

        this.setText("PLACE PIPE");
        this.setEnabled(false);
        this.setVisible(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.placePipe();
        });
    }

    public boolean canPerform() {
        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        return Controller.instance.selectedFields.size() == 0
                && player.getPosition() instanceof Pipe && ((Mechanic) player).getPump() != null;
    }
}
