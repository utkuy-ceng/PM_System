/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.PMInterface;
import Model.Product;
import ToolBox.ProductSingleton;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@ManagedBean(name = "products_controller")
public class ProductsController implements PMInterface<Object> {

    private SessionFactory factory;
    public String searchWord;
      public List<Product> productsList;
          public List<Product> searchList;

    public ProductsController() {
        this.factory = ProductSingleton.getInstance().getFactory();
    }


    @Override
    public void add(Object object) {

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        session.save(object);

        session.getTransaction().commit();

        session.close();

    }


    public List<Product> getProductsList() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        productsList = session.createQuery("from Product").getResultList();
        session.getTransaction().commit();
        session.close();
        return productsList;
    }



    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public List<Product> getSearchList() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        searchList= session.createQuery("From Product where name LIKE '" + "%" + searchWord + "%'").getResultList();
        session.close();
        return searchList;
    }

    // olmazsa hql 
    @Override
    public void delete(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("delete from Product where id=" + id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void buy(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("UPDATE Product SET total = total - 1 WHERE id=" + id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void addMore(int id) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("UPDATE Product SET total = total + 1 WHERE id=" + id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
