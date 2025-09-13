package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    private Connection con;

    public Doctor(Connection con) {
        this.con = con;

    }
    public void viewDoctors(){
        String query="select * from doctors";
        try{
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs= stmt.executeQuery();
            System.out.println("Doctors");
            System.out.println("+---------------+----------------+-------------------+");
            System.out.println("| Doctor Id     |     Name       |  Specialization   |");
            System.out.println("+---------------+----------------+-------------------+");
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String specialization=rs.getString("specialization");
                System.out.printf("|%-16s|%-16s|%-19s|\n",id,name,specialization);
                System.out.println("+---------------+----------------+-------------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getDoctorById(int id){
        String query="select * from doctors where id=?";
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

