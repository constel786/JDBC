import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//      1st step: Register Driver - (optional)
        Class.forName("org.postgresql.Driver");

//      2nd step: Create Connection to get connected to DB
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "eozer", "1234");

//      3rd step: Create Statement -- to execute SQL queries
        Statement statement = con.createStatement();

//      To test if we have created connection to DB
//      System.out.println("Connected successfully");

//      4th step: Execute SQL queries

//      5th step: Close connection and statement (to free up memory)
        statement.close();
        con.close();

//      --------------------------------
//      TASK 1: Create a table named "employee" with column names of "employee_id", employee_name", and "salary"
        
        boolean sql1 = statement.execute("CREATE TABLE employee (employee_id INT, employee_name VARCHAR(50), salary REAL)");
        
//      --execute() method returns boolean value and can be used for DDL (Data Definition Language) or DQL (Data Query Language)
//      --If it is used with DDL, it returns "false"
//      --If it is used with DQL (SELECT, etc.) it returns "true"

        System.out.println("sql1 = " + sql1);
    
//      TASK 2: Add VARCHAR(20) column name "city" to the employee table
        
        String query = "ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        boolean sql2 = statement.execute(query);

        System.out.println("sql2 = " + sql2);

//      TASK 3: Delete employee table from SCHEMA

//        String query1 = "DROP TABLE employee";
//        statement.execute(query1);

    }
}
