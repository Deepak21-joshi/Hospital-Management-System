package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection con;
    private Scanner sc;

    public Patient(Connection con, Scanner sc){
        this.con=con;
        this.sc=sc;
    }
    public void addPatient(){
        sc.nextLine();
        System.out.println("Enter your name: ");
        String name=sc.nextLine();
        System.out.println("Enter your age: ");
        int age=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your gender: ");
        String gender=sc.next();
        try{
            String query="insert into patients(name,age,gender) values(?,?,?)";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setInt(2,age);
            stmt.setString(3,gender);
            int rowaffected=stmt.executeUpdate();
            if(rowaffected>0){
                System.out.println("Patient addedd Successfully");
            }else{
                System.out.println("Failed to add patient");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPatient(){
        String query="select * from patients";
        try{
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            System.out.println("Patients");
            System.out.println("+---------------+----------------+---------+---------+");
            System.out.println("| Patient Id    |     Name       |   age   |  gender |");
            System.out.println("+---------------+----------------+---------+---------+");
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                String gender=rs.getString("gender");
                System.out.printf("|%-15s|%-16s|%-9s|%-9s|\n", id,name,age,gender);
                System.out.println("+---------------+----------------+---------+---------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatientById(int id){
        String query="select * from patients where id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
