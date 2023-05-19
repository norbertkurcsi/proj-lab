package GUI;

import proto.Field;

import java.security.InvalidParameterException;

public class FieldPosition {
    public final int fieldCountHorizontal=20;
    public final int fieldCountVertical=10;

    private int x;
    private int y;

    public FieldPosition(int x, int y) {
//        if(x<0 || x > fieldCountHorizontal || y < 0 || y > fieldCountVertical)
//            throw new InvalidParameterException();

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
