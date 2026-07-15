import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Cell {

    private int px;
    private int py;
    private int color;

    public Cell(int X, int Y, int color) {
        this.px = X;
        this.py = Y;
        this.color = color;
    }

    public int getX() {
        return px;
    }

    public int getY() {
        return py;
    }

    public int getColor() {
        return color;
    }

    public Shape getShape() {
        return new Ellipse2D.Double(px - 2.5, py - 2.5, 5, 5);
    }
}

