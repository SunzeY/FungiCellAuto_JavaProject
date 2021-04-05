import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Arrays;

public class Inf implements TableModel {
    private final String[] title = {
            "name", "index1", "index2", "index3"
    };
    private final String[][] data = new String[12][4];

    public Inf() {
        for (int i=0; i<data.length;i++) {
            Arrays.fill(data[i], "");
        }
    }

    public String[][] getData() {
        return this.data;
    }

    @Override
    public int getRowCount() {
        return 12;
    }

    @Override
    public int getColumnCount() {
        return title.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = (String) aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
