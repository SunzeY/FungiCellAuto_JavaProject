import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final long serialVersionUID = -5258995676212660595L;
    private static final int GRID_SIZE = 4;
    private final Field theField;

    public View(Field field) {
        theField = field;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GRAY);
        for(int row = 0;row<theField.getHeight();row++){
            g.drawLine(0,row*GRID_SIZE,theField.getWidth()*GRID_SIZE,row*GRID_SIZE);
        }
        for(int col = 0;col<theField.getWidth();col++){
            g.drawLine(col*GRID_SIZE,0,col*GRID_SIZE,theField.getHeight()*GRID_SIZE);
        }

        for (int row = 0; row<theField.getHeight(); row++) {
            for (int col = 0; col<theField.getWidth(); col++) {
                Cell fungi = theField.get(row, col);
                if (fungi != null) {
                    fungi.draw(g, col*GRID_SIZE, row*GRID_SIZE, GRID_SIZE,
                            (theField.getRemain(row, col)/ Field.BaseValue));
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(theField.getWidth()*GRID_SIZE+1, theField.getHeight()*GRID_SIZE+1);
    }
}
