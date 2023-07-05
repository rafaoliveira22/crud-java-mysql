package app.entity;

public class User{
    private String cpf;
    private String name;

    private String stack;

    private Integer age;

    private String status;
    private String resp;

    // DEFAULT CONSTRUCTOR
    public User(){}

    // CONSTRUCTOR
    public User(String cpf, String name, String stack, Integer age){
        this.cpf = cpf;
        this.name = name;
        this.stack = stack;
        this.age = age;
    }

    public User(String status, String resp){
        this.status = status;
        this.resp = resp;
    }

    // GETTERS AND SETTERS
    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getStack(){
        return stack;
    }

    public void setStack(String stack){
        this.stack = stack;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
