import java.sql.SQLException;
import java.util.Scanner;

//Service Layer is to handle business logic
public class StudentService {
    //To get access to methods in Repository Class
    StudentRepository repository = new StudentRepository();
    Scanner input = new Scanner(System.in);

    //Step 7: Method to call createTable from Repository Layer
    public void createTable() throws SQLException {
        repository.createTable();

    }
//Step 9: Method to register student
     public void saveStudent(){
         System.out.println("Name: ");
         String name = input.nextLine();
         System.out.println("Last Name: ");
         String lastName = input.nextLine();
         System.out.println("City: ");
         String city = input.nextLine();
         System.out.println("Age: ");
         int age = input.nextInt();
         input.nextLine(); //to call next enter/new line

//   After getting all data from user, we create new student object using info entered by user
         Student newStudent = new Student(name, lastName, city, age);
//   New student should be saved in our DB
         repository.save(newStudent);

     }

//Step 11: Method to display students in a list
    public void getAllStudents(){
        repository.findAll();

    }

//    Step 13: Method to find student by ID
    public Student getStudentById(int id){
        Student student = repository.findStudentById(id);
        return student;
    }

//    Step 15: Method to delete student by ID
    public void deleteStudent(int id){
        //We can handle error messages if there is no student found with ID
        repository.delete(id);
    }

//Step 17: Method to update student info
    public void updateStudent(int id){

//        Find student from table
    Student existingStudent = getStudentById(id);
    //        Check if user exists in table or not
    if(existingStudent == null){
        System.out.println("Student with id " +id+ " not found.");
    }else{//We have found one student and we can start updating
        //Ask user to enter new data to be updated
        System.out.println("Name: ");
        String name = input.nextLine();
        System.out.println("Last Name: ");
        String lastName = input.nextLine();
        System.out.println("City: ");
        String city = input.nextLine();
        System.out.println("Age: ");
        int age = input.nextInt();
        input.nextLine(); //to call next enter/new line

//      Start update fields of existing student date
        existingStudent.setName(name);
        existingStudent.setLastname(lastName);
        existingStudent.setCity(city);
        existingStudent.setAge(age);
//No need to set ID since it should remain the same

        repository.update(existingStudent);

        }
    }
}
