import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class FungiModule {
    private static Field theField;
    private static View theView;
    private static Integer[] hashTable;
    private static JTextArea error;
    public static FungiFactory f;
    private final dataRecord dataRecord;
    public static final HashMap<Integer, String> numToType = new HashMap<Integer, String>() {{
        put(1, "A");
        put(2, "B");
        put(3, "C");
        put(4, "D");
        put(5, "E");
        put(6, "F");
        put(7, "G");
    }};
    private static Inf a;

    public void initial1 () {
        dataRecord.cleanData();
        theField.clear();
    }

    public void initial2 () {
        dataRecord.cleanData();
        f = new FungiFactory(a.getData());
        theField.clear();
        for (int i = 1; i <= f.getFacNum(); i++) {
            for (int j = 0; j <4; j++ ) {
                theField.place(((int) (theField.getWidth()* (Math.random()))),
                                ((int) (theField.getHeight()* (Math.random()))),  f.makeFungi(numToType.get(i)));
            }
        }

    }

    public FungiModule (int size) {
        hashTable = new Integer[size];
        theField= new Field(size, size);
        for (int i = 0; i < size; i++){
            hashTable[i] = i;
        }
        dataRecord = new dataRecord(theField);
        initial1();
        theView = new View(theField);
        //GridLayout layout = new GridLayout(5, 5);
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(2, 2));
        frame.setTitle("Fungi Auto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(theView);

        //datatable
        a = new Inf();
        f = new FungiFactory(a.getData());
        JTable table = new JTable(a);
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane, BorderLayout.SOUTH);
        //datatable

        //inf
        error = new JTextArea(5, 10);
        frame.add(error, BorderLayout.SOUTH);
        error.setFont(new Font("标楷体", Font.BOLD, 16));
        //info

        //Control
        //FungiPara
        JPanel panel = new JPanel();
        JButton updateInf =  new JButton("update");
        panel.add(updateInf, BorderLayout.EAST);
        updateInf.addActionListener(e -> {
            try {
                initial2();
                theView.repaint();
                error.setText("");
            }
            catch (Exception a) {
                error.setText(a.toString());
            }
        });
        //FungiPara
        JButton btnStep =  new JButton("single");
        panel.add(btnStep, BorderLayout.NORTH);
        btnStep.addActionListener(e -> {
            step();
            theView.repaint();
        });
        JButton run =  new JButton("run");
        panel.add(run, BorderLayout.WEST);
        run.addActionListener(e -> {
            AutoPerform a = new AutoPerform(theField);
            Thread branch = new Thread(a);
            branch.start();
        });

        JButton record =  new JButton("record");
        panel.add(record, BorderLayout.SOUTH);
        record.addActionListener(e -> {
            try {
                dataRecord.writeData();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void step() {
        ArrayUtils.shuffle(hashTable);
        for (int col : hashTable) {
            ArrayUtils.shuffle(hashTable);
            for (int row : hashTable) {
                Cell cell = theField.get(row, col);
                if (cell != null) {
                    Fungi fungi = (Fungi) cell;
                    fungi.timeFlow();
                    theField.eat(row, col);
                    if (fungi.isAlive()) {
                        Fungi child = f.makeFungi(fungi.getType());
                        double probability = Math.random() * fungi.probRep();
                        if (probability>3.5) {
                            theField.placeRandomAdj(row, col, child);
                        }
                    }
                }
            }
        }
        dataRecord.recordData();
    }
}
