package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;
import java.util.Scanner;

@SpringBootApplication
public class SqlitejdbcmavenApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();
			stmt.executeUpdate("create table person (id integer, name string, age integer, address string, salary real)");
			stmt.executeUpdate("insert into person values(1, 'leo', 24, 'california', 1999)");
			stmt.executeUpdate("insert into person values(2, 'yui', 30, 'newyork', 3000)");
			System.out.println("Table created successfully");
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
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
				ResultSet rs = stmt.executeQuery("select * from person");

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

				String insertSql = String.format("insert into person values(%s, '%s', %s, '%s', %s)",
						id2, name2, age2, address2, salary2);
//				String insertSql = String.format("INSERT INTO PERSON (ID,NAME,AGE,ADDRESS,SALARY) " +
//						"VALUES (%s, '%s', %s, '%s', %s );", id2, name2, age2, address2, salary2);
				stmt.executeUpdate(insertSql);
			}
			else if(selection == 3) {
				System.out.println("Please enter user ID");
				int id3 = scanner.nextInt();
				System.out.println("Please enter your salary");
				int salary3 = scanner.nextInt();
				String updateSql = String.format("UPDATE PERSON set SALARY = %s where ID=%s;", salary3, id3);
				stmt.executeUpdate(updateSql);
			}
			else if(selection == 4) {
				System.out.println("Please enter user ID");
				int id4 = scanner.nextInt();
				String deleteSql = String.format("DELETE from PERSON where ID=%s;", id4);
				stmt.executeUpdate(deleteSql);
			}

			stmt.close();
			c.commit();
			c.close();

			System.out.println("End");
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

}
