package Controller;

import ToolBox.ClassSingleton;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@ManagedBean
@ApplicationScoped
public class ProductsTypeController {
    
    private SessionFactory factory;
    private List<String> type_list;
    
    public ProductsTypeController(){
                this.factory = ClassSingleton.getInstance().getFactory();
                
                getAllTypes();
        
    }

    public List<String> getType_list() {
        return type_list;
    }

    public void setType_list(List<String> type_list) {
        this.type_list = type_list;
    }
    
    
    public void getAllTypes(){
        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();
        
        String hql = "SELECT type FROM Classes";
        
        
        Query query = session.createQuery(hql);
        
        type_list = query.list();
        
        session.getTransaction().commit();
        
        session.close();
    }
    
}
