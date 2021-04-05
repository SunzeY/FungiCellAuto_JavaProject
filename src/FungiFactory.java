import java.util.Arrays;
import java.util.HashMap;

public class FungiFactory {
    private static final int pareNum = 3;
    private final int numOfKind;
    private static HashMap<String, Double> outData;
    private static HashMap<String, double[]> info;
    public FungiFactory(String[][] data) {
        int i = 0;
        int j;
        int rowNum = 0;
        info = new HashMap<>();
        while(!data[i][0].equals("")) {
            rowNum = i;
            j = 1;
            double[] a = new double[4];
            while( j<pareNum ) {
                a[j] = Double.parseDouble(data[i][j]);
                j++;
            }
            info.put(data[i][0], a);
            i++;
        }
        numOfKind = i;
        rowNum++;
        outData = new HashMap<>();
        for (i=0; i<rowNum;i++) {
            outData.put(data[i][0], 0.0);
        }
    }

    public Fungi makeFungi(String type) {
        outData.put(type, outData.get(type) + 1.0);
        return new Fungi(type, info.get(type));
    }

    public int getFacNum() {
        return this.numOfKind;
    }
}
