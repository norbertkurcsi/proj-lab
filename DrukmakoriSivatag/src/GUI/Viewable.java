package GUI;

import javax.swing.*;
import java.util.List;

public interface Viewable {
    public void update();

    public Object getModelObject();

    public FieldPosition getPosition();
}
