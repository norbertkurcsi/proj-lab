package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface Viewable {
    public void update();

    public Object getModelObject();

    public Point getPosition();
}
