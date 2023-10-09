package data;

import domein.OVChipkaart;
import domein.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO{
    public Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }
    @Override
    public boolean save(Product product) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.save(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.delete(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        try {
            session.beginTransaction();
            List<Product> products = session.createQuery(
                            "SELECT p " +
                                    "FROM Product p " +
                                    "JOIN FETCH p.ovChipkaarten ovp " +
                                    "WHERE :ovChipkaart MEMBER OF ovp", Product.class)
                    .setParameter("ovChipkaart", ovChipkaart)
                    .list();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        try {
            session.beginTransaction();
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            session.getTransaction().commit();
            return products;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
