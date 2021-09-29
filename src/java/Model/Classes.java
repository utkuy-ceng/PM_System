package Model;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ManagedBean
@Table(name="classes")
public class Classes {
    
    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "type")
    private String type;

    public Classes() {
    }

    public Classes(int id, String type) {
        this.id = id;
        this.type = type;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserType{" + "id=" + id + ", type=" + type + '}';
    }
    
    public void reset(){
        this.type = "";
    }
}
