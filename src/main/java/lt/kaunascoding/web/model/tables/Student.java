package lt.kaunascoding.web.model.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    private int id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String phone;
    private String email;

    public Student() {
    }

    public Student(ResultSet eilute) {
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
