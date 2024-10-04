package assignment;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Bai1.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                System.out.println("Không tìm thấy config.properties");
                return;
            }

            Properties properties = new Properties();
            properties.load(inputStream);     // Đọc các thuộc tính trong file config
            int hideNumber = Integer.parseInt(properties.getProperty("soCanNhap"));
            int maxEnteringNumber = Integer.parseInt(properties.getProperty("soLanNhapToiDa"));

            Scanner sc = new Scanner(System.in);
            int enteringNumber = 0;
            System.out.print("Nhập số: ");
            while (enteringNumber < maxEnteringNumber) {
                int inputNumber = sc.nextInt();
                if (inputNumber == hideNumber) {
                    System.out.println("Nhập thành công!");
                    break;
                }
                if ( ++enteringNumber == maxEnteringNumber) {
                    System.out.println("Nhập lỗi!");
                    break;
                } else System.out.print("Nhập lại: ");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
