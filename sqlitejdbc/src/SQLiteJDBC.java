import java.sql.*;
import java.util.Scanner;

public class SQLiteJDBC {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String tablesql = "CREATE TABLE USER " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(tablesql);
            System.out.println("Table created successfully");
        } catch ( Exception e ) {
            System.out.println("Table USER already exists");
        }

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please select options below:");
            System.out.println("[1] View all users");
            System.out.println("[2] Insert new user");
            System.out.println("[3] Update user salary");
            System.out.println("[4] Delete user");

            int selection = scanner.nextInt();
            if (selection == 1){
                ResultSet rs = stmt.executeQuery( "SELECT * FROM USER;" );

                while ( rs.next() ) {
                    int id1 = rs.getInt("id");
                    String name1 = rs.getString("name");
                    int age1  = rs.getInt("age");
                    String address1 = rs.getString("address");
                    float salary1 = rs.getFloat("salary");

                    System.out.println( "ID = " + id1 );
                    System.out.println( "NAME = " + name1 );
                    System.out.println( "AGE = " + age1 );
                    System.out.println( "ADDRESS = " + address1 );
                    System.out.println( "SALARY = " + salary1 );
                    System.out.println();
                }
                rs.close();
            }
            else if( selection == 2 ){
                System.out.println("Please enter user ID");
                String id2 = scanner.next();
                System.out.println("Please enter your name");
                String name2 = scanner.next();
                System.out.println("Please enter your age");
                String age2= scanner.next();
                System.out.println("Please enter your address");
                String address2 = scanner.next();
                System.out.println("Please enter your salary");
                String salary2 = scanner.next();
                String insertsql = String.format("INSERT INTO USER (ID,NAME,AGE,ADDRESS,SALARY) " +
                        "VALUES (%s, '%s', %s, '%s', %s );", id2, name2, age2, address2, salary2);
                stmt.executeUpdate(insertsql);
            }
            else if(selection == 3) {
                System.out.println("Please enter user ID");
                int id3 = scanner.nextInt();
                System.out.println("Please enter your salary");
                int salary3 = scanner.nextInt();
                String updatesql = String.format("UPDATE USER set SALARY = %s where ID=%s;", salary3, id3);
                stmt.executeUpdate(updatesql);
            }
            else if(selection == 4) {
                System.out.println("Please enter user ID");
                int id4 = scanner.nextInt();
                String deletesql = String.format("DELETE from USER where ID=%s;", id4);
                stmt.executeUpdate(deletesql);
            }

            stmt.close();
            c.commit();
            c.close();

            System.out.println("End");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

//        try {
//            Scanner scanner = new Scanner(System.in);
//
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:test.db");
//            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");
//
//            stmt = c.createStatement();
//
//            System.out.println("Please enter user ID");
//            int updateid = scanner.nextInt();
//            System.out.println("Please enter your salary");
//            int updatesalary = scanner.nextInt();
//            String updatesql = String.format("UPDATE USER set SALARY = %s where ID=%s;", updatesalary, updateid);
//            stmt.executeUpdate(updatesql);
//            c.commit();
//
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
//
//            while ( rs.next() ) {
//                int id = rs.getInt("id");
//                String  name = rs.getString("name");
//                int age  = rs.getInt("age");
//                String  address = rs.getString("address");
//                float salary = rs.getFloat("salary");
//
//                System.out.println( "ID = " + id );
//                System.out.println( "NAME = " + name );
//                System.out.println( "AGE = " + age );
//                System.out.println( "ADDRESS = " + address );
//                System.out.println( "SALARY = " + salary );
//                System.out.println();
//            }
//            rs.close();
//            stmt.close();
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//        System.out.println("Operation done successfully");
    }
}