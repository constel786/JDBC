import java.sql.*;


//Class which will be connected to the DB (Connection, Statement, Prepared Statement)
public class StudentRepository {
    private Connection con;
    private Statement statement;
    private PreparedStatement prs;

    //Step 3: Method to create connection
    private void getConnection() {
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "eozer", "1234");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Step 4: Method to create Statement
    private void getStatement(){
        try {
            this.statement = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Step 5: Method to create PreparedStatement
    private void getPreparedStatement(String query){
        try {
            this.prs = con.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Step 6: Method to create the table
    public void createTable() throws SQLException {
        getConnection(); //create a connection
        getStatement(); //to be able to run queries

        try {
            String query = "CREATE TABLE IF NOT EXISTS tbl_student (id SERIAL, name VARCHAR(50), lastname VARCHAR(50), city VARCHAR(50), age INT)";
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    Step 10: Method to persist data into database
    public void save(Student newStudent) {
        getConnection();
        String query = "INSERT INTO tbl_student (name, lastname, city, age) VALUES (?, ?, ?, ?)";
        getPreparedStatement(query);
        try {
            prs.setString(1, newStudent.getName());
            prs.setString(2, newStudent.getLastname());
            prs.setString(3, newStudent.getCity());
            prs.setInt(4, newStudent.getAge());

            prs.executeUpdate(); //to persist/store values into table/DB
            System.out.println("Student registered successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    Step 12: Method to bring all students from DB
    public void findAll() {
        getConnection();
        String query = "SELECT * FROM tbl_student";
        getStatement();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print("ID: " + resultSet.getInt("id"));
                System.out.print(" Name: " + resultSet.getString("name"));
                System.out.print(" Last Name: " + resultSet.getString("lastname"));
                System.out.print(" City: " + resultSet.getString("city"));
                System.out.print(" Age: " + resultSet.getInt("age"));
                System.out.println(); //To start from new line for info about next student
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    Step 14: Method to find student from table by provided ID
    public Student findStudentById(int id) {
        getConnection();
        Student student = null;
        String query = "SELECT * FROM tbl_student WHERE id = ?";

        try {
            getPreparedStatement(query);
            prs.setInt(1, id);
            //Data type coming from table is ResultSet
            ResultSet resultSet = prs.executeQuery();

            //If resultSet has some value (there is student info) then we need to map resultSet to student
            while(resultSet.next()){
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setLastname(resultSet.getString("lastname"));
                student.setCity(resultSet.getString("city"));
                student.setAge(resultSet.getInt("age"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return student;
    }

//    Step 16: Method that will run query to delete student from table
    public void delete(int id) {
        getConnection();
        String query = "DELETE FROM tbl_student WHERE id = ?";
        getPreparedStatement(query);
        try {
            prs.setInt(1, id);
            int deletedRows = prs.executeUpdate(); //Number of deleted rows
            if(deletedRows>0){
                System.out.println("Student with ID: " + id + " is deleted successfully.");
            }else {
                System.out.println("Student with ID: " + id + " could not be found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }

//    Step 18: Method to run update query to update student information
    public void update(Student newStudentInfo) {
    getConnection();
    String query = "UPDATE tbl_student SET name=?, lastname=?, city=?, age=? WHERE id=? ";
    getPreparedStatement(query);
        try {
            prs.setString(1, newStudentInfo.getName());
            prs.setString(2, newStudentInfo.getLastname());
            prs.setString(3, newStudentInfo.getCity());
            prs.setInt(4, newStudentInfo.getAge());
            prs.setInt(4, newStudentInfo.getId());
            int updated = prs.executeUpdate();
            if(updated>0){
                System.out.println("Student updated successfully.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                prs.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }



    }
}
