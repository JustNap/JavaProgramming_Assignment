package untar.java.tugas1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryApp extends JFrame {
    private ArrayList<Model> products;
    private TabelModel tabelModel;
    private JTable table;
    private JTextField textCode, textName, textQty, textPrice;

    public InventoryApp(){
        setTitle("Inventory App (Memory Only)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        products = new ArrayList<>();
        tabelModel = new TabelModel(products);
        table = new JTable(tabelModel);

        JPanel input = new JPanel(new GridLayout(4, 3, 5, 5));
        input.add(new JLabel("Kode :"));
        textCode = new JTextField();
        input.add(textCode);

        input.add(new JLabel("Nama :"));
        textName = new JTextField();
        input.add(textName);

        input.add(new JLabel("Qty :"));
        textQty = new JTextField();
        input.add(textQty);

        input.add(new JLabel("Price :"));
        textPrice = new JTextField();
        input.add(textPrice);

        JButton btnTambah = new JButton("Tambah");
        JButton btnHapus = new JButton("Hapus Produk Terpilih");

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(input, BorderLayout.CENTER);
        panelAtas.add(btnTambah, BorderLayout.SOUTH);

        JPanel panelBawah = new JPanel();
        panelBawah.add(btnHapus);

        add(panelAtas, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnHapus, BorderLayout.SOUTH);

        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = textCode.getText();
                    String name = textName.getText();
                    int qty = Integer.parseInt(textQty.getText());
                    Double price = Double.parseDouble(textPrice.getText());

                    Model product = new Model(code, name, qty, price);
                    tabelModel.Add(product);

                    textCode.setText("");
                    textName.setText("");
                    textQty.setText("");
                    textPrice.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Input Tidak Valid");
                }
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pilihBaris = table.getSelectedRow();
                if (pilihBaris != -1){
                    tabelModel.remove(table.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(null, "pilih data yang ingin dihapus");
                }
            }
        });
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryApp().setVisible(true);
        });
    }
}
