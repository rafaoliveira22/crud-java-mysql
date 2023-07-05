package app.dao;

import app.entity.User;
import app.entity.Defines;
import app.jdbc.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDAO{
    private Connection con;
    public void insertUser(User user){
        System.out.println("\nInsert user");

        PreparedStatement pst = null;
        try{
            con = DBManager.getCon();
            String query = "INSERT INTO tb_users (tb_users_cpf, tb_users_name, tb_users_stack, tb_users_age) VALUES(?, ?, ?, ?)";

            pst = con.prepareStatement(query);
            pst.setString(1, user.getCpf());
            pst.setString(2, user.getName());
            pst.setString(3, user.getStack());
            pst.setInt(4, user.getAge());
            pst.executeUpdate();

            System.out.println("userInserted: " + user);

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                pst.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }//try..catch...finally
    }//insertUser()

    public void deleteUser(User user){
        System.out.println("\nDelete user");

        PreparedStatement pst = null;
        try{
            con = DBManager.getCon();
            String query = "DELETE FROM tb_users WHERE tb_users_cpf = ?";

            pst = con.prepareStatement(query);
            pst.setString(1, user.getCpf());
            pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                pst.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }//try..catch...finally
    }//deleteUser()

    public void updateUser(User user, User userWithData, Integer fieldToUpdate){
        System.out.println("\nUpdate user");

        //constants


        PreparedStatement pst = null;
        Scanner sc = new Scanner(System.in);

        try{
            con = DBManager.getCon();
            String query = "UPDATE tb_users SET ";

            switch(fieldToUpdate){
                case Defines.CPF:
                    query += "tb_users_cpf= '" + userWithData.getCpf() + "'";
                    break;

                case Defines.NAME:
                    query += "tb_users_name= '" + userWithData.getName() + "'";
                    break;

                case Defines.STACK:
                    query += "tb_users_stack= '" + userWithData.getStack() + "'";
                    break;

                case Defines.AGE:
                    query += "tb_users_age= " + userWithData.getAge();
                    break;
                default:
                    System.out.println(userWithData.getResp());
                    break;
            }
            query += " where tb_users = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, user.getCpf());

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                pst.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }//try..catch...finally
    }//updateUser()

    public User selectUser(User user) {
        System.out.println("\nSelect user");

        PreparedStatement pst = null;
        ResultSet rs = null;
        User userSelected = null;
        try {
            con = DBManager.getCon();
            String query = "SELECT tb_users_cpf as cpf, tb_users_name as name, tb_users_stack as stack, tb_users_age as age " +
                            "from tb_users where tb_users_cpf = ?";

            pst = con.prepareStatement(query);
            pst.setString(1, user.getCpf());
            rs = pst.executeQuery();

            if (rs.next()) {
                userSelected = new User(rs.getString("cpf"), rs.getString("name"), rs.getString("stack"), rs.getInt("age"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }//try..catch...finally
        return userSelected;
    }//selectUser

}//UserDAO
