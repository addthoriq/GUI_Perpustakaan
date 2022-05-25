import javax.swing.*;
import java.awt.BorderLayout;
import java.sql.*;

public class Tampilan extends JFrame{
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
        JButton btn_keluar = new JButton();

        String column[] = {"Nomor", "Kode", "Peminjam", "Buku", "Tgl Pinjam", "Harus Kembali", "Tgl Kembali", "Status"};
        String data [][] = {
                {"1", "", "", "", "", "", "", ""},
                {"2", "", "", "", "", "", "", ""},
                {"3", "", "", "", "", "", "", ""},
                {"4", "", "", "", "", "", "", ""},
                {"5", "", "", "", "", "", "", ""},
                {"6", "", "", "", "", "", "", ""},
                {"7", "", "", "", "", "", "", ""},
                {"8", "", "", "", "", "", "", ""},
                {"9", "", "", "", "", "", "", ""},
                {"10", "", "", "", "", "", "", ""},
        };
        JTable tabel = new JTable(data, column);
        JScrollPane sp = new JScrollPane();

        JPanel jp = new JPanel();

        Tampilan() throws ClassNotFoundException, SQLException{
                setTitle("Winamp versi Thoriq");
                setSize(840, 450);
                setLayout(null);

                // Kode Peminjaman
                lb_kode.setBounds(15, 10, 200, 30);
                lb_kode.setText("Kode Peminjaman");
                tf_kode.setBounds(200, 10, 100, 30);
                tf_kode.setText("");
                // End Kode Peminjaman

                // Nama Peminjaman
                lb_nama.setBounds(15, 50, 200, 30);
                lb_nama.setText("Nama Peminjam");
                tf_nama.setBounds(200, 50, 100, 30);
                tf_nama.setText("");
                // End Nama Peminjaman

                // Buku
                lb_buku.setBounds(15, 90, 200, 30);
                lb_buku.setText("Nama Buku");
                tf_buku.setBounds(200, 90, 100, 30);
                tf_buku.setText("");
                // End Buku

                // Tanggal Pinjam
                lb_tglPinjam.setBounds(15, 140, 200, 30);
                lb_tglPinjam.setText("Tanggal Pinjam");
                tf_tglPinjam.setBounds(200, 140, 100, 30);
                tf_tglPinjam.setText("");
                // End Tanggal Pinjam

                // Deadline
                lb_deadline.setBounds(15, 190, 200, 30);
                lb_deadline.setText("Tanggal Harus Kembali");
                tf_deadline.setBounds(200, 190, 100, 30);
                tf_deadline.setText("");
                // End Deadline

                // Status
                lb_status.setBounds(15, 240, 200, 30);
                lb_status.setText("Status Peminjaman");
                stts_dipinjam.setBounds(200, 240, 100, 30);
                stts_dikembalikan.setBounds(300, 240, 125, 30);
                stts_belumKembali.setBounds(430, 240, 200, 30);
                bg.add(stts_dipinjam);
                bg.add(stts_dikembalikan);
                bg.add(stts_belumKembali);
                // End Status


                // Button
                btn_cari.setBounds(400, 20, 100, 40);
                btn_cari.setText("Cari");
                btn_simpan.setBounds(400, 70, 100, 40);
                btn_simpan.setText("Simpan");
                btn_hapus.setBounds(400, 120, 100, 40);
                btn_hapus.setText("Hapus");
                btn_keluar.setBounds(400, 170, 100, 40);
                btn_keluar.setText("Keluar");
                // End Button

                // Tabel
                tabel.setBounds(0, 0, 800, 200);

                hub.koneksiMysql();
                getData();
                sp.setViewportView(tabel);
                // End Tabel


                // Panel
                jp.setBounds(15, 300, 800, 100);
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
                add(lb_status);
                add(stts_dipinjam);
                add(stts_dikembalikan);
                add(stts_belumKembali);
                add(btn_cari);
                add(btn_simpan);
                add(btn_hapus);
                add(btn_keluar);
                add(jp, BorderLayout.CENTER);

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

                                tabel.setValueAt(Integer.toString(nomor), baris, 0);
                                tabel.setValueAt(kode, baris, 1);
                                tabel.setValueAt(nama, baris, 2);
                                tabel.setValueAt(buku, baris, 3);
                                tabel.setValueAt(tgl_pinjam, baris, 4);
                                tabel.setValueAt(deadline, baris, 5);
                                tabel.setValueAt(tgl_kembali, baris, 6);
                                tabel.setValueAt(status, baris, 7);
                                nomor++;
                                baris++;
                        }
                        hub.closeConnection();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
}
