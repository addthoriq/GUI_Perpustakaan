import java.sql.*;
import javax.swing.JOptionPane;

public class Bridge {
    private String url, usr, pass;
    private Connection koneksi;
    private Statement stt;

    Bridge(){
        this.url = "jdbc:mysql://localhost:3306/perpustakaan";
        this.usr = "root";
        this.pass = "123";
    }

    public void koneksiMysql() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        koneksi = (Connection) DriverManager.getConnection(this.url, this.usr, this.pass);
    }

    public Statement openConnection() throws SQLException{
        return koneksi.createStatement();
    }

    public void closeConnection() throws SQLException {
        koneksi.close();
    }

    public ResultSet getAll() throws SQLException {
        stt = openConnection();
        return stt.executeQuery("SELECT * FROM peminjaman");
    }

    public ResultSet getSearch(String key) throws SQLException {
        stt = openConnection();
        return stt.executeQuery("SELECT * FROM peminjaman WHERE kode_pinjam="+key);
    }

    public void insertData(String kode, String nama, String buku, String tglPinjam, String harusKembali, String kembali, String status) throws SQLException{
        PreparedStatement stat = koneksi.prepareStatement("INSERT INTO peminjaman(kode_pinjam, nama_peminjam, judul_buku, tanggal_pinjam, harus_kembali, tanggal_kembali, status) VALUES (?,?,?,?,?,?,?)");
        try {
            stat.setString(1, kode.toUpperCase());
            stat.setString(2, nama);
            stat.setString(3, buku);
            stat.setString(4, tglPinjam);
            stat.setString(5, harusKembali);
            stat.setString(6, kembali);
            stat.setString(7, status.toLowerCase());
            stat.executeUpdate();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Data Tidak Disimpan", "Gagal", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        stat.close();
    }

    public void updateData(String kode, String nama, String buku, String tglPinjam, String harusKembali, String kembali,
            String status) throws SQLException {
        PreparedStatement stat = koneksi.prepareStatement(
                "UPDATE peminjaman SET kode_pinjam = ?, nama_peminjam = ?, judul_buku = ?, tanggal_pinjam = ?, harus_kembali = ?, tanggal_kembali = ?, status = ? WHERE kode_pinjam = ?");
        try {
            stat.setString(1, kode.toUpperCase());
            stat.setString(2, nama);
            stat.setString(3, buku);
            stat.setString(4, tglPinjam);
            stat.setString(5, harusKembali);
            stat.setString(6, kembali);
            stat.setString(7, status.toLowerCase());
            stat.setString(8, kode.toUpperCase());
            stat.executeUpdate();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Data Tidak Disimpan", "Gagal",
            // JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        stat.close();
    }
}
