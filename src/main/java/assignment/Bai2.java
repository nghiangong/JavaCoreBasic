package assignment;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Bai2.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                System.out.println("Không tìm thấy config.properties");
                return;
            }
            Properties properties = new Properties();
            properties.load(inputStream);     // Đọc các thuộc tính trong file config
            int level1 = Integer.parseInt(properties.getProperty("level1"));
            int level1Price = Integer.parseInt(properties.getProperty("level1Price"));
            int level2 = Integer.parseInt(properties.getProperty("level2"));
            int level2Price = Integer.parseInt(properties.getProperty("level2Price"));
            int level3 = Integer.parseInt(properties.getProperty("level3"));
            int level3Price = Integer.parseInt(properties.getProperty("level3Price"));

            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập số điện: ");
            int electricNumber = sc.nextInt();
            int electricPrice = 0;

            if (electricNumber <= level2) {
                electricPrice +=  electricNumber * level1Price;
            } else if (electricNumber <= level3) {
                electricPrice = level2*level1Price + (electricNumber - level2)*level2Price;
            } else
                electricPrice = level2*level1Price + (level3 - level2)*level2Price + (electricNumber - level3)*level3Price;

            System.out.println("Tiền điện: "+electricPrice);
        }

        catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
