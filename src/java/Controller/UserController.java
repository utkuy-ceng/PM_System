package Controller;

import Model.User;
import ToolBox.FactorySingleton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

@ManagedBean
@ApplicationScoped
public class UserController{

    
    
    private SessionFactory factory;
    private String loginError;
    private boolean isLoginErrorVisible;
    private boolean isDeleteSucces = false;
    private List<User> list;
    
    
    public UserController() {
        this.factory = FactorySingleton.getInstance().getFactory();
        loadUsers();
    }
    
    public boolean isIsDeleteSucces() {
        return isDeleteSucces;
    }

    public void setIsDeleteSucces(boolean isDeleteSucces) {
        this.isDeleteSucces = isDeleteSucces;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public boolean isIsLoginErrorVisible() {
        return isLoginErrorVisible;
    }

    public void setIsLoginErrorVisible(boolean isLoginErrorVisible) {
        this.isLoginErrorVisible = isLoginErrorVisible;
    }
    
    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public void addUser(User user) throws NoSuchAlgorithmException {

        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();
        
        String toHash = getSha256(user.getPassword());
        user.setPassword(toHash);
        
        
        // user'ı kaydet
        session.save(user);

        // transaciton'ı tamamla
        session.getTransaction().commit();
        
        
        // session'ı kapat
        session.close();

        user.reset();
    }

    public void deleteUser(int userID) {

        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();

        // user'ı sil
        int a = session.createQuery("delete from User where id=" + userID).executeUpdate();
        
        if(a==0){
            this.isDeleteSucces = false;
        }else{
            this.isDeleteSucces = true;
        }
        
        // transaction'ı tamamla
        session.getTransaction().commit();

        //session'ı kapat
        session.close();
        
        loadUsers();
        
    }

    public User findUser(int userID) {

        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();

        // user nesnesini id ile al
        User temp = session.get(User.class, userID);

        // transaction'ı tamamla
        session.getTransaction().commit();

        // session'ı kapat
        session.close();

        return temp;
    }
    
    public void loadUsers(){
        
        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();
        
        list = session.createQuery("from User").getResultList();
        
        session.getTransaction().commit();
        
        session.close();
    }
    
    public List<User> getUsers() {
        // session oluştur
        Session session = factory.getCurrentSession();

        // transaction başlat
        session.beginTransaction();
        
        this.list = session.createQuery("from User").getResultList();
        
        session.getTransaction().commit();
        
        session.close();
        
        return this.list;
    }

    public String doLogin(User user) {

        
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String hql = "FROM User WHERE username = :username_";
        Query query = session.createQuery(hql);
        query.setParameter("username_", user.getUsername());
        List<User> list = query.getResultList();
        
        session.getTransaction().commit();
        
        session.close();
        
        
        if(list.isEmpty()){
            this.loginError = "Bu kullanıcı adı ve şifreyle kayıtlı bir kullanıcı bulunmamaktadır.";
            return "";
        }
        
        String toHash = getSha256(user.getPassword());
        boolean isAdmin = list.get(0).getType().equals("admin");
        
        if (list.get(0).getPassword().equals(toHash) && isAdmin) {
            this.isLoginErrorVisible = false;
            this.loginError="";
            user.setPassword("");
            user.setName("");
            return "AdminMainPage";
        } else if (list.get(0).getPassword().equals(toHash) && !isAdmin) {
            this.isLoginErrorVisible = false;
            this.loginError ="";
            user.setPassword("");
            user.setName("");
            return "HomePage";
        } else {
            this.isLoginErrorVisible = true;
            this.loginError = " Wrong username/password!";
            return "";
        }
    }

    private String getSha256(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
    

}
