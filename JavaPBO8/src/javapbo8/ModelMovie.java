package javapbo8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ModelMovie {
    String DBurl = "jdbc:mysql://localhost/movie_db";
    String DBUsername = "root";
    String DBPassword = "";
    Connection conn;
    Statement stat;
    public ModelMovie() {
    try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl, DBUsername, DBPassword);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }
    public String[][] MovieList(){  
        try{
                int jmlData = 0;
                String data[][] = new String[getBanyakData()][5]; 
                String query = "Select * from movie"; 
                ResultSet resultSet = stat.executeQuery(query);
                while (resultSet.next()){
                    data[jmlData][0] = resultSet.getString("Judul"); 
                    data[jmlData][1] = resultSet.getString("Alur");                
                    data[jmlData][2] = resultSet.getString("Penokohan");
                    data[jmlData][3] = resultSet.getString("Akting");
                    data[jmlData][4] = resultSet.getString("Nilai");
                    jmlData++;
                }
                return data;
            }catch(SQLException e){
                System.out.println(e.getMessage());
                System.out.println("SQL Error");
                return null;
            }
    }
    public void insertmovie(String judul, String alur, String penokohan, String akting){
     int jmlData=0;
     double falur=Float.parseFloat(alur);
     double fpenokohan=Float.parseFloat(penokohan);
     double fakting=Float.parseFloat(akting);
     double nilai = (falur + fpenokohan +fakting)/3;
        try {
           String query = "Select * from movie WHERE judul = '" + judul + "' "; 
           System.out.println(judul + " " + falur + " " + fpenokohan + " " + fakting + " " + nilai);
           ResultSet resultSet = stat.executeQuery(query);   
           while (resultSet.next()){ 
                jmlData++;
            }
            if (jmlData==0) {
                query = "INSERT INTO movie VALUES('"+judul+"','"+falur+"','"+fpenokohan+"','"+fakting+"','"+nilai+"')";
                stat = (Statement) conn.createStatement();
                stat.executeUpdate(query); 
                System.out.println("Berhasil ditambahkan");
                JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
            }
            else {
                JOptionPane.showMessageDialog(null, "Data sudah tersedia");
            }
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    public void updateMovie(String judul, String alur, String penokohan, String akting){
        int jmlData=0;
        double falur=Float.parseFloat(alur);
        double fpenokohan=Float.parseFloat(penokohan);
        double fakting=Float.parseFloat(akting);
        double nilai = (falur + fpenokohan +fakting)/3; 
        try {
           String query = "Select * from movie WHERE judul= '" + judul + "' "; 
           ResultSet resultSet = stat.executeQuery(query);
           while (resultSet.next()){ 
                jmlData++;
            }
             if (jmlData==1) {
                query = "UPDATE movie SET alur='" + falur + "', Penokohan='" + fpenokohan + "', Nilai='" + nilai + "', Akting = '" + akting +"' WHERE judul='" + judul +"'";
                stat = (Statement) conn.createStatement();
                stat.executeUpdate(query); 
                System.out.println("Berhasil Update");
                JOptionPane.showMessageDialog(null, "Data Berhasil Update");
             }
             else {
                 JOptionPane.showMessageDialog(null, "Data Kosong");
             }
        } catch (Exception sql) {
            System.out.println(sql.getMessage());   
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
     public void deleteMovie (String judul) {
        try{
            String query = "DELETE FROM movie WHERE judul = '"+judul+"'";
            stat = conn.createStatement();
            stat.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
            
        }catch(SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
public int getBanyakData(){
        int jmlData = 0;
        try{
            stat = conn.createStatement();
            String query = "Select * from movie";
            ResultSet resultSet = stat.executeQuery(query);
            while (resultSet.next()){ 
                jmlData++;
            }
            return jmlData;  
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
}