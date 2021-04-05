import java.awt.*;
import java.util.HashMap;

public class Fungi implements Cell{
    private final String type;
    private boolean alive;
    private final double eatRate;
    private final double repRate;
    private double lifeSpan;
    private double Agg;
    private double beInf = 0;
    private double age;
    private static final HashMap<String, Color> COLORMAP = new HashMap<String, Color>() {{
        put("A", new Color(0, 0, 255));
        put("B", new Color(0, 255, 0));
        put("C", new Color(255, 0, 0));
        put("D", new Color(255, 0, 255));
        put("E", new Color(255, 255, 0));
        put("F", new Color(0, 255, 255));
        put("G", new Color(255, 123, 11));
    }};
    private final Color color;

    public Fungi (String typ, double[] para) {
        this.type = typ;
        this.eatRate = para[1];
        this.repRate = para[2];
        //this.Agg = para[2];
        this.color = COLORMAP.get(typ);
        alive = true;
    }

    public double inf() {
        return this.Agg + repRate;
    }

    public void beInf(double inf) {
        this.beInf = inf;
    }

    public double probRep() {
        return this.repRate - beInf;
    }

    public double eat() {
        return this.eatRate;
    }

    public int getTypeNum() {
        return this.type.charAt(0) - 'A';
    }


    public void draw(Graphics g, int x, int y, int size, double whiteDegree) {
        g.drawRect(x, y, size, size);
        if ( alive ) {
            g.setColor(new Color((int)(this.color.getRed()*whiteDegree),
                    (int)(this.color.getGreen()*whiteDegree),
                    (int)(this.color.getBlue()*whiteDegree)));

            g.fillRect(x, y, size, size);
        }
    }

    public void die() {
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void timeFlow() {
        this.age = this.age;
        if (age > lifeSpan) {
            die();
        }
    }

    public String getType() {
        return this.type;
    }
}
