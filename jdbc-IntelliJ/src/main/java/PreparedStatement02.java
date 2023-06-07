import java.sql.*;

public class PreparedStatement02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "eozer", "1234");
        Statement statement = con.createStatement();

        //TASK-1. Using PreparedStatement, delete students who are from Mathematics department, from students table.
        System.out.println("************ TASK-1 **************");
        String query1 = "DELETE FROM students WHERE department ILIKE ?";
        PreparedStatement prs = con.prepareStatement(query1);
        prs.setString(1, "mathematics");
        int numOfUpdatedRows = prs.executeUpdate();
        System.out.println("numOfUpdatedRows = " + numOfUpdatedRows);



        //TASK-2. Insert software engineering department using Prepared Statement into departments table.
        // (id = 5006, pass_grade=475, campus='South')

        System.out.println("************ TASK-2 **************");

//        prs.close();
        statement.close();
        con.close();
    }
}
