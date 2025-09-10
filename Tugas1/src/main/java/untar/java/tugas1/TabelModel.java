package untar.java.tugas1;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabelModel extends AbstractTableModel {

    private ArrayList<Model> products;
    private String[] columnNames = {"kode", "Nama", "Qty", "Harga"};

    public TabelModel(ArrayList<Model> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model value = products.get(rowIndex);

        switch (columnIndex){
            case 0:
                return value.getCode();
            case 1:
                return value.getName();
            case 2:
                return value.getQty();
            case 3:
                return value.getPrice();
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void Add(Model add){
        products.add(add);
        fireTableRowsInserted(products.size() - 1, products.size() -1);
    }

    public void remove(int index){
        if (index >=0 && index < products.size()){
            products.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
}
