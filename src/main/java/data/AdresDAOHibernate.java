package data;

import domein.Adres;
import domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO{

    public Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.save(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.update(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.delete(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        try {
            session.beginTransaction();
            Adres adres = (Adres) session.createQuery("FROM Adres WHERE reiziger = :reiziger")
                    .setParameter("reiziger", reiziger)
                    .uniqueResult();
            session.getTransaction().commit();
            return adres;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        try {
            session.beginTransaction();
            List<Adres> adressen = session.createQuery("FROM Adres", Adres.class).list();
            session.getTransaction().commit();
            return adressen;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
