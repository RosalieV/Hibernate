package data;

import domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO{
    public Session session;

    public ReizigerDAOHibernate(Session session) {
        this.session = session;
    }


    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.save(reiziger);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.update(reiziger);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.delete(reiziger);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Reiziger findByID(int id) throws SQLException {
        try {
            session.beginTransaction();
            Reiziger reiziger = (Reiziger) session.createQuery(
                            "FROM Reiziger  WHERE reiziger_id = :id", Reiziger.class)
                    .setParameter("id", id)
                    .uniqueResult();
            session.getTransaction().commit();
            return reiziger;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum)throws SQLException {
        try {
            session.beginTransaction();
            List<Reiziger> reizigers = session.createQuery(
                            "FROM Reiziger r WHERE r.geboortedatum = :datum", Reiziger.class)
                    .setParameter("datum", java.sql.Date.valueOf(datum))
                    .list();
            session.getTransaction().commit();
            return reizigers;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        try {
            session.beginTransaction();
            List<Reiziger> reizigers = session.createQuery(
                            "FROM Reiziger", Reiziger.class)
                    .list();
            session.getTransaction().commit();
            return reizigers;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
