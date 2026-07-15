
public class Manhattan implements DistanceType {

    @Override
    public double distance(Cell c, int X, int Y) {
        return Math.abs(c.getX() - X) + Math.abs(c.getY() - Y);
    }
}
