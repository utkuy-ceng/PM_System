package ToolBox;

import Model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactorySingleton {
    
    private SessionFactory factory;
    private static FactorySingleton instance;

    
    private FactorySingleton(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml") //hibernate.cfg.xml diye belirtmek şart değil hibernate kendisi arayıp bu dosyasyı bulur normalde
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
    
    public static FactorySingleton getInstance(){
        
        if(instance == null){
            instance = new FactorySingleton();
        }
        return instance;
    }
    
    public SessionFactory getFactory(){
        return factory;
    }
}
