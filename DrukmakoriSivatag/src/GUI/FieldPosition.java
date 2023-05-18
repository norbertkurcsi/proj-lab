package GUI;

import java.security.InvalidParameterException;

public class FieldPosition {
    public final int fieldCountHorizontali=20;
    public final int fieldCountVerticali=10;

    private int x;
    private int y;

    public FieldPosition(int x, int y) {
        if(x<0 || x > fieldCountHorizontali || y < 0 || y > fieldCountVerticali)
            throw new InvalidParameterException();

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
