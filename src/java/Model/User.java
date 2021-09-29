package Model;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@ManagedBean(name = "user_bean")
public class User {

    @Id // primary key'i set ediyoruz
    //@GeneratedValue(strategy = GenerationType.IDENTITY) bu etiket primary key'in nasıl oluşturuculağını belirtir . IDENTITY = AUTO_INCREMENT
    // id'nin nasıl oluşturulabileceğini kendin de belirtebilirsin. IdentifierGenerator interfaceini implent et.
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "secret_answer")
    private String secretAnswer;

    @Column(name = "type")
    private String type;

    public User() {
    }

    public User(int id, String name, String surname, String username, String password, String secretAnswer, String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.secretAnswer = secretAnswer;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password + ", secretAnswer=" + secretAnswer + ", type=" + type + '}';
    }
    
    public void reset(){
        this.name = null;
        this.surname = null;
        this.password = null;
        this.secretAnswer = null;
        this.type = null;
        this.username = null;
    }
}
