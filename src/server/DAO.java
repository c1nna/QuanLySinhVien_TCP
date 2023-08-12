/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Student;
/**
 *
 * @author Admin
 */
public class DAO {
    private Connection conn;

    public DAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/quanlysinhvien";
            conn = DriverManager.getConnection(dbUrl, "root", "Quelong.0468");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean addStudent(Student s){
        String q = "insert into student values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, s.getId());
            ps.setString(2, s.getName());
            ps.setDouble(3, s.getGpa());
            ps.setInt(4, s.getAge());
            ps.setString(5, s.getSex());
            ps.setString(6, s.getAddress());
            ps.setString(7, s.getDes());
            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Student> getAll(){
        List<Student> list = new ArrayList<>();
        String sql = "select * from student";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student st = new Student(rs.getString("id"), 
                        rs.getString("name"), 
                        rs.getDouble("gpa"), 
                        rs.getInt("age"), 
                        rs.getString("sex"), 
                        rs.getString("address"), 
                        rs.getString("des"));
                list.add(st);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean updateStudent(Student s){
        String q = "update student set name = ?, gpa = ?, age = ?, sex = ?, address = ?, des = ? where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, s.getName());
            ps.setDouble(2, s.getGpa());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getSex());
            ps.setString(5, s.getAddress());
            ps.setString(6, s.getDes());
            ps.setString(7, s.getId());
            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delStudent(Student stu){
        String sql = "delete from student where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getId());
            ps.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Student> getStudentByName(Student stu){
        List<Student> list = new ArrayList<>();
        String sql = "select * from student where name like '%" + stu.getName() + "%'";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student st = new Student(rs.getString("id"), 
                        rs.getString("name"), 
                        rs.getDouble("gpa"), 
                        rs.getInt("age"), 
                        rs.getString("sex"), 
                        rs.getString("address"), 
                        rs.getString("des"));
                list.add(st);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
}
