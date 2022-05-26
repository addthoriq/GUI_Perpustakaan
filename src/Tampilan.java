import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.sql.*;

public class Tampilan extends JFrame implements ActionListener {
    Bridge hub = new Bridge();

    JLabel lb_kode = new JLabel();
    JLabel lb_nama = new JLabel();
    JLabel lb_buku = new JLabel();
    JLabel lb_tglPinjam = new JLabel();
    JLabel lb_deadline = new JLabel();
    JLabel lb_kembali = new JLabel();
    JLabel lb_status = new JLabel();

    JTextField tf_kode = new JTextField();
    JTextField tf_nama = new JTextField();
    JTextField tf_buku = new JTextField();
    JTextField tf_tglPinjam = new JTextField();
    JTextField tf_deadline = new JTextField();
    JTextField tf_kembali = new JTextField();

    JRadioButton stts_dipinjam = new JRadioButton("Dipinjam");
    JRadioButton stts_dikembalikan = new JRadioButton("Dikembalikan");
    JRadioButton stts_belumKembali = new JRadioButton("Belum Kembali");
    ButtonGroup bg = new ButtonGroup();

    JButton btn_cari = new JButton();
    JButton btn_simpan = new JButton();
    JButton btn_hapus = new JButton();
    JButton btn_reset = new JButton();
    JButton btn_keluar = new JButton();

    TabelModelnya modelTabel = new TabelModelnya();
    JTable tabel = new JTable(modelTabel);

    TableColumnModel tab_colModel = tabel.getColumnModel();

    JPanel jp = new JPanel();
    JPanel jp2 = new JPanel();
    JLabel lb_gbr;
    
    Tampilan() throws ClassNotFoundException, SQLException, Exception {
        setTitle("RESPONSI PAG - PERPUSTAKAAN | 201055001");
        setSize(880, 450);
        setLayout(null);
        
        // Gambar header
        BufferedImage gbr = ImageIO.read(new File("src/Header.png"));
        lb_gbr = new JLabel(new ImageIcon(gbr));
        lb_gbr.setBounds(0, 0, 850, 100);
        jp2.setBounds(5, 10, 850, 100);
        jp2.add(lb_gbr);
        // END Gambar header

        // Kode Peminjaman
        lb_kode.setBounds(15, 110, 200, 30);
        lb_kode.setText("Kode Peminjaman");
        tf_kode.setBounds(200, 110, 150, 30);
        tf_kode.setText("");
        // End Kode Peminjaman

        // Nama Peminjaman
        lb_nama.setBounds(15, 145, 200, 30);
        lb_nama.setText("Nama Peminjam");
        tf_nama.setBounds(200, 145, 150, 30);
        tf_nama.setText("");
        // End Nama Peminjaman

        // Buku
        lb_buku.setBounds(15, 180, 200, 30);
        lb_buku.setText("Nama Buku");
        tf_buku.setBounds(200, 180, 150, 30);
        tf_buku.setText("");
        // End Buku

        // Tanggal Pinjam
        lb_tglPinjam.setBounds(370, 110, 200, 30);
        lb_tglPinjam.setText("Tanggal Pinjam");
        tf_tglPinjam.setBounds(600, 110, 150, 30);
        tf_tglPinjam.setText("");
        // End Tanggal Pinjam

        // Deadline
        lb_deadline.setBounds(370, 145, 200, 30);
        lb_deadline.setText("Tanggal Harus Kembali");
        tf_deadline.setBounds(600, 145, 150, 30);
        tf_deadline.setText("");
        // End Deadline

        // Tgl Kembali
        lb_kembali.setBounds(370, 180, 200, 30);
        lb_kembali.setText("Tanggal Pengembalian");
        tf_kembali.setBounds(600, 180, 150, 30);
        tf_kembali.setText("");
        // End

        // Status
        lb_status.setBounds(15, 215, 200, 30);
        lb_status.setText("Status Peminjaman");
        stts_dipinjam.setBounds(200, 215, 100, 30);
        stts_dipinjam.setActionCommand("dipinjam");
        stts_dikembalikan.setBounds(300, 215, 125, 30);
        stts_dikembalikan.setActionCommand("dikembalikan");
        stts_belumKembali.setBounds(430, 215, 200, 30);
        stts_belumKembali.setActionCommand("belum kembali");
        bg.add(stts_dipinjam);
        bg.add(stts_dikembalikan);
        bg.add(stts_belumKembali);
        bg.setSelected(stts_dipinjam.getModel(), true);
        // End Status

        // Button
        btn_cari.setBounds(15, 260, 125, 30);
        btn_cari.setText("Cari");
        btn_cari.addActionListener(this);
        btn_simpan.setBounds(165, 260, 125, 30);
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(this);
        btn_hapus.setBounds(315, 260, 125, 30);
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(this);
        btn_reset.setBounds(475, 260, 125, 30);
        btn_reset.setText("Reset");
        btn_reset.addActionListener(this);
        btn_keluar.setBounds(625, 260, 125, 30);
        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(this);
        // End Button

        // Tabel
        tabel.setBounds(0, 0, 850, 200);
        tabel.setFillsViewportHeight(true);
        // tab_colModel.getColumn(0).setPreferredWidth(850);
        JScrollPane sp = new JScrollPane(tabel);

        hub.koneksiMysql();
        getData();
        // sp.setViewportView(tabel);
        // End Tabel

        // Panel
        jp.setBounds(15, 300, 850, 100);
        jp.setLayout(new BorderLayout());
        jp.add(sp);
        // End Panel

        add(lb_nama);
        add(tf_nama);
        add(lb_kode);
        add(tf_kode);
        add(lb_buku);
        add(tf_buku);
        add(lb_tglPinjam);
        add(tf_tglPinjam);
        add(lb_deadline);
        add(tf_deadline);
        add(lb_kembali);
        add(tf_kembali);
        add(lb_status);
        add(stts_dipinjam);
        add(stts_dikembalikan);
        add(stts_belumKembali);
        add(btn_cari);
        add(btn_simpan);
        add(btn_hapus);
        add(btn_reset);
        add(btn_keluar);
        add(jp, BorderLayout.CENTER);
        add(jp2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void getData() {
        try {
            ResultSet res = hub.getAll();
            int baris = 0;
            int nomor = 1;
            while (res.next()) {
                String kode = res.getString("kode_pinjam");
                String nama = res.getString("nama_peminjam");
                String buku = res.getString("judul_buku");
                String tgl_pinjam = res.getString("tanggal_pinjam");
                String deadline = res.getString("harus_kembali");
                String tgl_kembali = res.getString("tanggal_kembali");
                String status = res.getString("status");

                modelTabel.setValueAt(Integer.toString(nomor), baris, 0);
                modelTabel.setValueAt(kode, baris, 1);
                modelTabel.setValueAt(nama, baris, 2);
                modelTabel.setValueAt(buku, baris, 3);
                modelTabel.setValueAt(tgl_pinjam, baris, 4);
                modelTabel.setValueAt(deadline, baris, 5);
                modelTabel.setValueAt(tgl_kembali, baris, 6);
                modelTabel.setValueAt(status.toUpperCase(), baris, 7);
                nomor++;
                baris++;
            }
            // hub.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        modelTabel.fireTableDataChanged();
    }

    public void cariData(String yangDicari) throws SQLException {
        try {
            Statement stt = hub.openConnection();
            ResultSet res = stt.executeQuery("SELECT * FROM peminjaman WHERE kode_pinjam = '" + yangDicari + "'");
            while (res.next()) {
                String kode = res.getString("kode_pinjam");
                String nama = res.getString("nama_peminjam");
                String buku = res.getString("judul_buku");
                String tglPinjam = res.getString("tanggal_pinjam");
                String deadline = res.getString("harus_kembali");
                String kembali = res.getString("tanggal_kembali");
                String status = res.getString("status");

                tf_kode.setText(kode);
                tf_nama.setText(nama);
                tf_buku.setText(buku);
                tf_tglPinjam.setText(tglPinjam);
                tf_deadline.setText(deadline);
                tf_kembali.setText(kembali);

                if (status.equals("dipinjam")) {
                    stts_dipinjam.setSelected(true);
                } else if (status.equals("dikembalikan")) {
                    stts_dikembalikan.setSelected(true);
                } else {
                    stts_belumKembali.setSelected(true);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada!", "Data tidak ada!", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sumberBtn = e.getSource();
        String kode = tf_kode.getText().toUpperCase();
        String nama = tf_nama.getText();
        String buku = tf_buku.getText();
        String tglPinjam = tf_tglPinjam.getText();
        String deadline = tf_deadline.getText();
        String kembali = tf_kembali.getText();
        String status = bg.getSelection().getActionCommand(); // bg.getSelection().getActionCommand();

        if (sumberBtn == btn_cari) {
            if (kode.equals("")) {
                JOptionPane.showMessageDialog(this, "Harap isi terlebih dahulu!", "Responsi",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    cariData(kode.trim());
                } catch (SQLException e1) {
                    // e1.printStackTrace();
                    // JOptionPane.showMessageDialog(null, "Data Tidak Ada!", "Data tidak ada!",
                    // JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (sumberBtn == btn_simpan) {
            // status = bg.getSelection().toString();
            if (kode.equals("") && nama.equals("") && buku.equals("") && tglPinjam.equals("") && deadline.equals("") && kembali.equals("") && status.equals("")) {
                JOptionPane.showMessageDialog(this, "Harap isi terlebih dahulu!", "Responsi",JOptionPane.WARNING_MESSAGE);
            } else {
                for (int index = 0; index < tabel.getRowCount(); index++) {
                    if (kode.equals(tabel.getValueAt(index, 1))) {
                        // Jalankan Update Data
                        try {
                            hub.updateData(kode, nama, buku, tglPinjam, deadline, kembali, status);
                            getData();
                        } catch (SQLException e1) {
                            // e1.printStackTrace();
                        }
                        getData();
                    } else {
                        // Jalankan Insert
                        try {
                            hub.insertData(kode, nama, buku, tglPinjam, deadline, kembali, status);
                            getData();
                        } catch (SQLException e1) {
                            // e1.printStackTrace();
                        }
                    }
                }
            }

        } else if (sumberBtn == btn_hapus) {
            try {
                hub.deleteData(kode);
                getData();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                // e1.printStackTrace();
            }
        } else if (sumberBtn == btn_reset) {
            tf_kode.setText("");
            tf_nama.setText("");
            tf_buku.setText("");
            tf_tglPinjam.setText("");
            tf_deadline.setText("");
            tf_kembali.setText("");
        } else {
            System.exit(0);
        }

    }

    public static class TabelModelnya extends AbstractTableModel {

        private final String[] columns = { "Nomor", "Kode", "Peminjam", "Buku", "Tgl Pinjam", "Harus Kembali", "Tgl Kembali","Status" };

        private final Object[][] data = {
            {"1","","","","","","",""},
            {"2","","","","","","",""},
            {"3","","","","","","",""},
            {"4","","","","","","",""},
            {"5","","","","","","",""},
            {"6","","","","","","",""},
            {"7","","","","","","",""},
            {"8","","","","","","",""},
            {"9","","","","","","",""},
            {"10","","","","","","",""},
        };

        @Override
        public int getColumnCount() {
            // TODO Auto-generated method stub
            return columns.length;
        }

        @Override
        public int getRowCount() {
            // TODO Auto-generated method stub
            return data.length;
        }

        @Override
        public String getColumnName(int column){
            return columns[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex){
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public void setValueAt(Object value, int row, int col){
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            // TODO Auto-generated method stub
            return data[rowIndex][columnIndex];
        }

    }

}
