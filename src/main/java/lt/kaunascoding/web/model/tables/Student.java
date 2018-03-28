package lt.kaunascoding.web.model.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    public int id;
    public String name;
    public String surname;
    public String phone;
    public String email;

    public Student(ResultSet eilute){
        try {
            id = eilute.getInt("id");
            name = eilute.getString("name");
            surname = eilute.getString("surname");
            phone = eilute.getString("phone");
            email = eilute.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
