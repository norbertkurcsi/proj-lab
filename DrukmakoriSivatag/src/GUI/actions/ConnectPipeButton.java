package GUI.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import GUI.Controller;
import proto.*;

public class ConnectPipeButton extends ActionButton {
    public ConnectPipeButton() {
        super();

        this.setContentAreaFilled(false);
        this.setRolloverEnabled(true);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setText("CONNECT PIPE");
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.connectPipe();
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

        if(!(player.getPosition() instanceof Pipe) || !(selectedFields.get(0) instanceof FieldNode))
            return false;

        Pipe pipe = (Pipe)player.getPosition();
        Field node = selectedFields.get(0); // The FieldNode to connect the pipe to

        return !pipe.hasNeighbour(node);
    }
}
