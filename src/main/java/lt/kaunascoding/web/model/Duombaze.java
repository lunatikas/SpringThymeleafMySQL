package lt.kaunascoding.web.model;

import lt.kaunascoding.web.model.tables.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Duombaze {
    private Connection _con;
    private Statement _st;

    public Duombaze() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            _con = DriverManager.getConnection("jdbc:mysql://192.168.100.100:3306/kcs", "root", "root");
            _st = _con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<Student>();
        try {
            _st = _con.createStatement();
            ResultSet set = _st.executeQuery("SELECT * FROM `students`;");
            while (set.next()) {
                result.add(new Student(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
