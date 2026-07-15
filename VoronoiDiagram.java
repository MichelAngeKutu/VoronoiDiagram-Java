import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class VoronoiDiagram extends JFrame {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    public VoronoiDiagram(int cells, int size, DistanceType distType) {
        super("Voronoi Diagram");
        setBounds(0, 0, size, size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        Random rand = new Random();
        Cell[] cellList = new Cell[cells];

        // Create random cells
        for (int i = 0; i < cells; i++) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            int color = rand.nextInt(16777215);
            cellList[i] = new Cell(x, y, color);
        }

        // Assign each pixel to nearest cell
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                int closest = 0;
                double bestDist = distType.distance(cellList[0], x, y);

                for (int i = 1; i < cells; i++) {
                    double d = distType.distance(cellList[i], x, y);
                    if (d < bestDist) {
                        bestDist = d;
                        closest = i;
                    }
                }

                image.setRGB(x, y, cellList[closest].getColor());
            }
        }

        // Draw cell points
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        for (Cell c : cellList) {
            g.fill(c.getShape());
        }

        // Save PNG
        try {
            ImageIO.write(image, "png", new File("voronoi.png"));
        } catch (IOException e) {
            // ignore per lab instructions
        }
    }

    public void drawVoronoi(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        drawVoronoi(g);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Voronoi Diagram Creator");
        System.out.print("How many points? ");
        int cells = in.nextInt();

        int size = 1000;

        System.out.println("1. Manhattan");
        System.out.println("2. Euclidean");
        System.out.println("3. Minkowski");
        System.out.print("Which distance type? ");
        int choice = in.nextInt();

        DistanceType dist;

        switch (choice) {
            case 1:
                dist = new Manhattan();
                break;
            case 2:
                dist = new Euclidean();
                break;
            case 3:
                System.out.print("Enter p for Minkowski: ");
                double p = in.nextDouble();
                dist = new Minkowski(p);
                break;
            default:
                dist = new Euclidean();
        }

        in.close();

        new VoronoiDiagram(cells, size, dist).setVisible(true);
    }
}
