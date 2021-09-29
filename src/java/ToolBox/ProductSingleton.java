package ToolBox;

import Model.User;
import Model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProductSingleton {
    
    private SessionFactory factory;
    private static ProductSingleton instance;

    
    private ProductSingleton(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml") //hibernate.cfg.xml diye belirtmek şart değil hibernate kendisi arayıp bu dosyasyı bulur normalde
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }
    
    public static ProductSingleton getInstance(){
        
        if(instance == null){
            instance = new ProductSingleton();
        }
        return instance;
    }
    
    public SessionFactory getFactory(){
        return factory;
    }
}
