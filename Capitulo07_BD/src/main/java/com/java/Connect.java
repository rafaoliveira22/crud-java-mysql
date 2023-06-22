package com.java;
import java.sql.*;
import java.util.*;

public class Connect{

    // VARIÁVEIS DE CONEXÃO
    private static final String DB_URL = "jdbc:mysql://localhost/database";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    //OPERAÇÕES
    private static final int INSERT_OPERATION = 1;
    private static final int DELETE_OPERATION = 2;
    private static final int UPDATE_OPERATION = 3;
    private static final int SELECT_OPERATION = 4;
    private static final int EXIT_OPERATION = 5;


    // MÉTODO PARA INSERIR USUARIOS NO BD
    public static void insertUser(Connection con) throws SQLException{
        System.out.println("\nINSERÇÃO DE USUÁRIO");

        Scanner sc = new Scanner(System.in);
        String query = "";
        PreparedStatement pst = null;

        Map<String, String> fields = new LinkedHashMap<>(){{
            put("cpf", "");
            put("name", "");
            put("stack", "");
        }};

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            System.out.println(entry.getKey().toUpperCase() + ": "); fields.replace(entry.getKey(), sc.nextLine());
        }//for input

        query = "INSERT INTO tb_users (tb_users_cpf, tb_users_stack, tb_users_name) VALUES(?, ?, ?)";
        pst = con.prepareStatement(query);

        Integer index = 1;
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            pst.setString(index, entry.getValue());
            index++;
        }//for setString

        pst.executeUpdate();
        pst.close();
    } //insertUser()

    // MÉTODO PARA DELETAR USUARIOS DO BD
    public static void deleteUser(Connection con) throws SQLException{
        System.out.println("\nDELEÇÃO DE USUÁRIO. DIGITE O NOME E O CPF DO USUÁRIO A SER DELETADO");

        Scanner sc = new Scanner(System.in);
        String cpf = "", query = "";
        PreparedStatement pst = null;

        System.out.println("CPF: "); cpf = sc.nextLine();

        query = "DELETE FROM tb_users WHERE tb_users_cpf = ?";

        pst = con.prepareStatement(query);
        pst.setString(1, cpf);
        pst.executeUpdate();
        pst.close();
    } //deleteUser()

    // MÉTODO PARA ATUALIZAR USUARIOS DO BD
    public static void updateUser(Connection con) throws SQLException{
        System.out.println("\nATUALIZAÇÃO DE USUÁRIO");

        Scanner sc = new Scanner(System.in);
        String current_cpf = "", new_data = "", query = "";
        Integer op = 0;
        PreparedStatement pst = null;

        System.out.println("CPF ATUAL: "); current_cpf = sc.nextLine();
        System.out.println("Deseja atualizar qual dado?\n1- CPF\n2- NOME\n3- STACK\nOpção: "); op = sc.nextInt();

        query = "UPDATE tb_users SET ";
        System.out.println("NOVO VALOR: "); new_data = sc.nextLine();

        if(op == 1) query += "tb_users_cpf= '" + new_data + "'";
        else if(op == 2) query += "tb_users_name= '" + new_data + "'";
        else if(op == 3) query += "tb_users_stack= '" + new_data + "'";
        else System.out.println("A opção " + op + " é inválida");

        query += " WHERE tb_users_cpf = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, current_cpf);
        pst.executeUpdate();
        pst.close();
    } //updateUser()

    // MÉTODO PARA SELECIONAR TODOS OS USUARIOS DO BD
    public static void selectAllUsers(Connection con) throws SQLException{
        System.out.println("\nSELEÇÃO DE TODOS OS USUÁRIOS");

        String query = "", name = "", cpf = "", stack = "";
        Statement st = con.createStatement();
        ResultSet rs = null;

        query = "SELECT * FROM tb_users";
        rs = st.executeQuery(query);
        if(rs == null){
            System.out.println("[Sem usuários cadastrados.]");
        }
        while(rs.next()){
            name = rs.getString("tb_users_name");
            cpf = rs.getString("tb_users_cpf");
            stack = rs.getString("tb_users_stack");
            System.out.println("[" + cpf +  "," + name + "," + stack + "]");
        }
        rs.close();
        st.close();
    } //selectUser()

    public static void main(String[] args){
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            Scanner sc = new Scanner(System.in);
            Integer op = 0;

            do{
                System.out.println("\nDeseja realizar qual operação?\n1- INSERT\n2- DELETE\n3- UPDATE\n4- SELECT ALL\n5- SAIR\nOpção: "); op = sc.nextInt();
                switch(op){
                    case INSERT_OPERATION:
                        insertUser(con);
                        break;
                    case DELETE_OPERATION:
                        deleteUser(con);
                        break;
                    case UPDATE_OPERATION:
                        updateUser(con);
                        break;
                    case SELECT_OPERATION:
                        selectAllUsers(con);
                        break;
                    case EXIT_OPERATION:
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }//switch
                Thread.sleep(1000);
            } while(op != EXIT_OPERATION);
            con.close();
        } catch(SQLException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//main
}//Connect