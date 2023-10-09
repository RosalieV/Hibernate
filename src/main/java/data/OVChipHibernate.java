package data;

import domein.OVChipkaart;
import domein.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OVChipHibernate implements OVChipDAO{

    public Session session;

    public OVChipHibernate(Session session) {
        this.session = session;
    }
    @Override
    public boolean save(OVChipkaart ovchipkaart) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.save(ovchipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(OVChipkaart ovchipkaart) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.update(ovchipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovchipkaart) throws SQLException {
        try {
            Transaction transaction = this.session.beginTransaction();
            session.delete(ovchipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        try {
            session.beginTransaction();
            List<OVChipkaart> ovChipkaarten = session.createQuery("FROM OVChipkaart", OVChipkaart.class).list();
            session.getTransaction().commit();
            return ovChipkaarten;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        try {
            session.beginTransaction();
            List<OVChipkaart> ovChipkaarten = session.createQuery("FROM OVChipkaart WHERE reiziger = :reiziger", OVChipkaart.class)
                    .setParameter("reiziger", reiziger)
                    .list();
            session.getTransaction().commit();
            return ovChipkaarten;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
