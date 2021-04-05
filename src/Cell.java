import java.awt.*;

public interface Cell {
    void draw(Graphics g, int x, int y, int size, double whiteDegree);
    double eat();
}
