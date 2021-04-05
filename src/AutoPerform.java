import javax.swing.*;
import java.io.IOException;

public class AutoPerform extends Thread{
    private final JFrame frame;
    private final Field field;
    private final dataRecord dataRecord;
    private static Integer[] hashTable;

    public AutoPerform(Field field) {
        dataRecord = new dataRecord(field);
        frame = new JFrame();
        this.field = field;
        hashTable = new Integer[field.getWidth()];
        for (int i = 0; i < field.getWidth(); i++) {
            hashTable[i] = i;
        }
    }

    public void run() {
        View theView = new View(field);
        frame.setTitle("AutoPerform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(theView);
        frame.pack();
        frame.setVisible(true);
        for(int i=0;i<300;i++){
            theView.repaint();
            step();
            dataRecord.recordData();
            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        try {
            dataRecord.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void step() {
        ArrayUtils.shuffle(hashTable);
        for (int col : hashTable) {
            for (int row : hashTable) {
                Cell cell = field.get(row, col);
                if (cell != null) {
                    Fungi fungi = (Fungi) cell;
                    fungi.timeFlow();
                    field.eat(row, col);
                    if (fungi.isAlive()) {
                        Fungi child = FungiModule.f.makeFungi(fungi.getType());
                        double probability = Math.random() * fungi.probRep();
                        if (probability>3.5) {
                            field.placeRandomAdj(row, col, child);
                        }
                    }
                }
            }
        }
    }
}
