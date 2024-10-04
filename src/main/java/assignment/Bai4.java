package assignment;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Bai2.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                System.out.println("Không tìm thấy config.properties");
                return;
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối database thành công!");
            add(connection);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void add(Connection connection) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số lượng sinh viên: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin sinh viên: ");
            System.out.print("Tên: ");
            String ten = sc.nextLine();

            System.out.print("Giới tính: ");
            String gioitinh = sc.nextLine();

            System.out.print("Quê quán: ");
            String quequan = sc.nextLine();

            System.out.print("Tuổi: ");
            int tuoi = Integer.parseInt(sc.nextLine());

            insertStudent(connection, ten, gioitinh, quequan, tuoi);
        }
        System.out.println("Hoàn thành");
    }

    public static void insertStudent(Connection con, String ten, String gioitinh, String quequan, int tuoi) {
        String query = "insert into student (ten, gioitinh, quequan, tuoi) values(?, ?, ?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ten);
            stmt.setString(2, gioitinh);
            stmt.setString(3, quequan);
            stmt.setInt(4, tuoi);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0)
                System.out.println("Thêm học sinh thành công!");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Tên học sinh đã tồn tại.");
            } else {
                e.printStackTrace();
            }
        }
    }
}
