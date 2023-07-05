package app;

import app.dao.UserDAO;
import app.entity.Defines;
import app.entity.User;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        UserDAO dao = new UserDAO();
        Scanner sc = new Scanner(System.in);
        Integer op = 0;
        do{
            System.out.println("\nDeseja realizar qual operação?\n1- INSERT\n2- DELETE\n3- UPDATE\n4- SELECT USER\n5- SAIR\nOpção: "); op = sc.nextInt();
            switch(op){
                case Defines.INSERT_OPERATION:
                    //insert
                    User userToInsert = new User("0", "Rafa", "Java", 18);
                    dao.insertUser(userToInsert);
                    break;
                case Defines.DELETE_OPERATION:
                    //delete
                    User userToDelete = new User();
                    userToDelete.setCpf("0");
                    dao.deleteUser(userToDelete);
                    break;
                case Defines.UPDATE_OPERATION:
                    //update
                    //chose user to update
                    User userToUpdate = new User();
                    userToUpdate.setCpf("0");

                    //chose data to update in user
                    User userWithDataToUpdate = new User();
                    Integer fieldChosen = 0;
                    if(fieldChosen == Defines.CPF) userWithDataToUpdate.setCpf("");
                    else if(fieldChosen == Defines.NAME) userWithDataToUpdate.setName("");
                    else if(fieldChosen == Defines.STACK) userWithDataToUpdate.setStack("");
                    else if(fieldChosen == Defines.AGE) userWithDataToUpdate.setAge(0);
                    else userWithDataToUpdate = new User("Erro", "Operação Inválida");

                    dao.updateUser(userToUpdate, userWithDataToUpdate, op);
                    break;
                case Defines.SELECT_OPERATION:
                    //select
                    User userToSelect = new User();
                    userToSelect.setCpf("0");
                    User userSelected = dao.selectUser(userToSelect);
                    System.out.println("userSelected: " + userSelected);
                    break;
                case Defines.EXIT_OPERATION:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }//switch
            Thread.sleep(1000);
        } while(op != Defines.EXIT_OPERATION);
    }//main
}//Main
