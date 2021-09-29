package ToolBox;

import Model.Classes;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClassSingleton {
    
    private SessionFactory factory;
    private static ClassSingleton instance;

    
    private ClassSingleton(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml") //hibernate.cfg.xml diye belirtmek şart değil hibernate kendisi arayıp bu dosyasyı bulur normalde
                .addAnnotatedClass(Classes.class)
                .buildSessionFactory();
    }
    
    public static ClassSingleton getInstance(){
        
        if(instance == null){
            instance = new ClassSingleton();
        }
        return instance;
    }
    
    public SessionFactory getFactory(){
        return factory;
    }
}
