package GUI.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import GUI.Controller;
import proto.*;

public class ConnectPipeButton extends ActionButton {
    public ConnectPipeButton() {
        super();

        this.setText("CONNECT PIPE");
        this.setEnabled(false);
        this.addActionListener((ActionEvent e) -> {
            Controller.instance.connectPipe();
        });
    }

    public boolean canPerform() {
        Player player = Controller.instance.selectedPlayer;
        if (!(player instanceof Mechanic))
            return false;

        List<Field> selectedFields = Controller.instance.selectedFields;
        if (selectedFields.size() != 2)
            return false;

        Field pipe = selectedFields.get(0); // Pipe to connect
        Field node = selectedFields.get(1); // The FieldNode to connect the pipe to
        if (pipe.hasNeighbour(node))
            return false;

        return pipe instanceof Pipe
                && node instanceof FieldNode;
    }
}
