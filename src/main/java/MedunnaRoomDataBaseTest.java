import java.sql.*;

import static org.junit.Assert.assertEquals;

public class MedunnaRoomDataBaseTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step: Register Driver - (optional)
        Class.forName("org.postgresql.Driver");

        //2.step : Create Connection to get connected to DB

        Connection con = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2",
                "select_user", "Medunna_pass_@6");

        //3. step: Create Statement -- to execute SQL queries
        Statement statement= con.createStatement();

        //Select * from room where id =60654;

        String sqlQuery="SELECT * FROM room where id =60654;";

        ResultSet resultSet= statement.executeQuery(sqlQuery);

        resultSet.next();

        String roomType=resultSet.getString("room_type");

        System.out.println("roomType = " + roomType);

        int roomNumber= resultSet.getInt("room_number");

        System.out.println("roomNumber = " + roomNumber);

        int price= resultSet.getInt("price");
        System.out.println("price = " + price);

        String description= resultSet.getString("description");
        System.out.println("description = " + description);

        boolean statusValue= resultSet.getBoolean("status");
        System.out.println("statusValue = " + statusValue);

        //assertion is done for verification and by the help of junit dependency you can use assertEquals() to test if the
        //expected value is same with the existing value.

        assertEquals("TWIN", roomType);

        assertEquals(false, statusValue);









    }



}
