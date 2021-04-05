import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class dataRecord {
    public double[][] data;
    public int dataCnt;
    private final Field field;
    private int step;

    public dataRecord(Field field) {
        this.field = field;
        step = 0;
        data = new double[8][2000];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2000; j++) {
                data[i][j] = 0;
            }
        }
        dataCnt = 0;
    }
    public void recordData() {
        double sum = 0;
        step += 1;
        int[] numOfFungi = new int[7];
        for (int i = 0; i < field.getWidth(); i++) {
            for (int j = 0; j < field.getHeight(); j++) {
                sum += field.getRemain(i, j);
                for (int k = 1; k <= FungiModule.f.getFacNum(); k++) {
                    Cell cell = field.get(i, j);
                    if (cell != null) {
                        Fungi fungi = (Fungi) cell;
                        numOfFungi[fungi.getTypeNum()] += 1;
                    }
                }
            }
        }
        data[0][step] = sum/((field.getWidth()* field.getHeight())*Field.BaseValue);
            for (int k = 0; k < FungiModule.f.getFacNum(); k++) {
                data[k+1][step] = numOfFungi[k];
            }
            dataCnt = step;
    }

    public void cleanData() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2000; j++) {
                data[i][j] = 0;
            }
        }
    }

    public void writeData() throws IOException {
        Double[][] data0 = new Double[FungiModule.f.getFacNum()+1][dataCnt];
        for (int i = 0; i <= FungiModule.f.getFacNum(); i++) {
            for (int j = 0; j < dataCnt; j++) {
                data0[i][j] = data[i][j];
            }
        }
        PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("./data_out.txt")));
        pw.print(Arrays.deepToString(data0));
        pw.close();
    }
}
