package Model;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "products")
@ManagedBean(name = "product_bean")


public class Product {
    @Id
    @Column(name="id")
    private int p_id;
    
    @Column(name="name")
    private String p_name;
    
    @Column(name="price")
    private double p_price;
    
    @Column(name="class")
    private String p_class;
    
    @Column(name="total")
    private int p_total;
    
    @Column(name="property")
    private String p_property;
    
    

    public Product() {
    }

    public Product(int p_id, String p_name, double p_price, String p_class, int p_total, String p_property) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_class = p_class;
        this.p_total = p_total;
        this.p_property = p_property;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public String getP_class() {
        return p_class;
    }

    public void setP_class(String p_class) {
        this.p_class = p_class;
    }

    public int getP_total() {
        return p_total;
    }

    public void setP_total(int p_total) {
        this.p_total = p_total;
    }

    public String getP_property() {
        return p_property;
    }

    public void setP_property(String p_property) {
        this.p_property = p_property;
    }
    
    @Override
    public String toString() {
        return "{" + "id=" + p_id + ", name=" + p_name + ", price=" + p_price + ", class=" + p_class + ", total=" + p_total + ", property=" + p_property + '}';
    }
    
}