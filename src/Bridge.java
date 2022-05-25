import java.sql.*;

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
        Class.forName("com.mysql.jdbc.Driver");
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
}
