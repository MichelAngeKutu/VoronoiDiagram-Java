
public class Minkowski implements DistanceType {

    private double p;

    public Minkowski(double p) {
        this.p = p;
    }

    @Override
    public double distance(Cell c, int X, int Y) {
        return Math.pow(
            Math.pow(Math.abs(c.getX() - X), p) +
            Math.pow(Math.abs(c.getY() - Y), p),
            1.0 / p
        );
    }
}
