import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;
public class cgpacal {
    public static void main(String[] args)throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cgpadata";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the Year : ");
            int yea = sc.nextInt();
            int i,n;

            System.out.printf("Enter the how many course in semester(include practical and extra course):\n");
            n = sc.nextInt();
            System.out.println("Enter name and register number");
            String nam = sc.next();
            String reg = sc.next();
            DecimalFormat s = new DecimalFormat();
                int cgrade;
                float cg=0;
                float cgs = 0;

                for(i=0;i<n;i++) {
                    System.out.printf("Enter the grade for course%d:", i+1);
                    cgrade = sc.nextInt();
                    System.out.printf("Enter the credits for course%d:",i+1);
                    int ccredit = sc.nextInt();
                    cgs +=  ccredit*cgrade;
                    cg += ccredit;
                }
            String na = "INSERT INTO cgpatab VALUES (?,?,ROUND(?,2));";
            PreparedStatement statement = conn.prepareStatement(na);
            float cgp = cgs/cg;
            statement.setString(1,nam);
            statement.setString(2,reg);
            statement.setDouble(3,cgp);
            System.out.println(String.format("CGPA:%.2f",cgp));
            statement.executeUpdate();
            conn.close();
        }
        catch (Exception u) {
            System.out.println("Error occured");
            u.printStackTrace();
        }
    }
    }

