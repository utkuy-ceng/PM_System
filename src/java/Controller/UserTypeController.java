package Controller;

import Model.UserType;
import ToolBox.FactorySingleton;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

@ManagedBean
@ApplicationScoped
public class UserTypeController {
    
    private SessionFactory factory;
    private List<String> type_list;
    
    public UserTypeController(){
        this.factory = FactorySingleton.getInstance().getFactory();
        getAllTypes();
    }

    public List<String> getType_list() {
        return type_list;
    }

    public void setType_list(List<String> type_list) {
        this.type_list = type_list;
    }
    
    public void addUserType(UserType usertype){
        
        Session session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        session.save(usertype);
        
        session.getTransaction().commit();
        
        session.close();
        
        usertype.reset();
    }
    
    public void getAllTypes(){
        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();
        
        String hql = "SELECT U.type FROM UserType U WHERE U.type='personel'";
        
        
        Query query = session.createQuery(hql);
        
        type_list = query.list();
        
        session.getTransaction().commit();
        
        session.close();
    }
    
}
