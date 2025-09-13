package HospitalManagementSystem;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class HospitalSystem{
    private static final String url="jdbc:mysql://localhost:3306/hospital";
    private static final String username="root";
    private static  final String password="Spring@boot5";


    public static void main(String[] args)throws ClassNotFoundException,SQLException{
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
     }catch (ClassNotFoundException e){
         System.out.println(e.getMessage());
     }
      try{
       Connection con=DriverManager.getConnection(url,username,password);
       Scanner sc=new Scanner(System.in);
       Patient p=new Patient(con,sc);
       Doctor d=new Doctor(con);
       while(true){
           System.out.println("***** HOSPITAL SYSTEM *****");
           System.out.println("1. Add Patient");
           System.out.println("2. View Patients");
           System.out.println("3. View Doctors");
           System.out.println("4. Book Appointment");
           System.out.println("5. Exit");
           System.out.println("Enter your choice");
           int choice=sc.nextInt();
           switch (choice){
               case 1:
                   p.addPatient();
                   System.out.println();
                   break;
               case 2:
                   p.viewPatient();
                   System.out.println();
                   break;
               case 3:
                   d.viewDoctors();
                   System.out.println();
                   break;
               case 4:
                   bookAppointment(p,d,con,sc);
                   System.out.println();
               case 5:
                   System.out.println("Exiting System");
                   return;
               default:
                   System.out.println("Enter valid choice");
                   break;
           }
       }

      }catch (SQLException e){
          e.printStackTrace();
      }
    }
    public static void bookAppointment(Patient p,Doctor d,Connection con,Scanner sc){
        System.out.println("Enter Patient id");
        int p_id= sc.nextInt();
        System.out.println("Enter Doctor id");
        int d_id=sc.nextInt();
        System.out.println("Enter Appointment Date (YYYY-MM-DD):");
        String date= sc.next();
        if(p.getPatientById(p_id) && (d.getDoctorById(d_id))){
            if(checkDocAvailability(d_id,date,con)){
                String query="insert into appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
                try{
                    PreparedStatement stmt=con.prepareStatement(query);
                    stmt.setInt(1,p_id);
                    stmt.setInt(2,d_id);
                    stmt.setString(3,date);
                    int rowsaffected=stmt.executeUpdate();
                    if(rowsaffected>0){
                        System.out.println("booked appointment");
                    }else{
                        System.out.println("failed to book appointment");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }else{
                System.out.println("Doc not available on this date");
            }
        }else{
            System.out.println("Either doc or patient not exist!");
        }
    }
    public static boolean checkDocAvailability(int d_id,String date,Connection con){
        String query="select count(*) from appointments where doctor_id=? and appointment_date=?";
        try{
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setInt(1,d_id);
            stmt.setString(2,date);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                int count=rs.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}