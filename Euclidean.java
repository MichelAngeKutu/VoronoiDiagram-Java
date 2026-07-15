
public class Euclidean implements DistanceType {

    @Override
    public double distance(Cell c, int X, int Y) {
        return Math.sqrt(
            Math.pow(c.getX() - X, 2) +
            Math.pow(c.getY() - Y, 2)
        );
    }
}

