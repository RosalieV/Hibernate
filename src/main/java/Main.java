import data.*;
import domein.Adres;
import domein.OVChipkaart;
import domein.Product;
import domein.Reiziger;
import org.hibernate.SessionFactory;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

    /**
     * Testklasse - deze klasse test alle andere klassen in deze package.
     *
     * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
     *
     * @author tijmen.muller@hu.nl
     */
    public class Main {
        // Creëer een factory voor Hibernate sessions.
        private static final SessionFactory factory;

        static {
            try {
                // Create a Hibernate session factory
                factory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }

        /**
         * Retouneer een Hibernate session.
         *
         * @return Hibernate session
         * @throws HibernateException
         */
        private static Session getSession() throws HibernateException {
            return factory.openSession();
        }

        public static void main(String[] args) throws SQLException {
            testFetchAll();

            getSession().beginTransaction();

            ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(getSession());
            AdresDAOHibernate adao = new AdresDAOHibernate(getSession());
            OVChipHibernate ovdao = new OVChipHibernate(getSession());
            ProductDAOHibernate pdao = new ProductDAOHibernate(getSession());

            testDAOHibernate(rdao, adao, ovdao, pdao);
        }

        /**
         * P6. Haal alle (geannoteerde) entiteiten uit de database.
         */
        private static void testFetchAll() {
            Session session = getSession();
            try {
                Metamodel metamodel = session.getSessionFactory().getMetamodel();
                for (EntityType<?> entityType : metamodel.getEntities()) {
                    Query query = session.createQuery("from " + entityType.getName());

                    System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                    for (Object o : query.list()) {
                        System.out.println("\n  " + o);
                    }
                    System.out.println();
                }
            } finally {
                session.close();
            }
        }

        private static void testDAOHibernate(ReizigerDAO rdao, AdresDAO adao, OVChipDAO ovdao, ProductDAO pdao ) throws SQLException {
            System.out.println("\n---------- Test Data.AdresDAO ----------");

            Session session = getSession();

            // Haal alle adressen op uit de database
            List<Adres> adressen = adao.findAll();
            System.out.println("[Test] Data.AdresDAO.findAll() geeft de volgende adressen:");
            for(Adres a : adressen) {
                System.out.println(a);
            };

            //Verwijder een adres uit de database
            Reiziger d = rdao.findByID(18);
            Adres boom = new Adres(18, "3572WP", "22", "Boomstraat", "Utrecht", d);
            System.out.print("\n[Test] Eerst " + adressen.size() + " adressen na de adao.delete() ");
            session.clear();
            adao.delete(boom);
            adressen = adao.findAll();
            System.out.println(adressen.size() + " adressen");


            //Voeg een adres toe aan de data base
            System.out.print("\n[Test] Eerst " + adressen.size() + " adressen na de AdresDao.save() ");
            adao.save(boom);
            adressen = adao.findAll();
            System.out.println(adressen.size() + " adressen\n");

            //Een adres update
            Reiziger Piccardo = rdao.findByID(5);
            Adres Vermeulenstraat = new Adres(5, "3572WP", "22", "Vermeulenstraat", "Utrecht", Piccardo);
            System.out.println("\n[Test] Straatnummer voor de adao.update(): " + Vermeulenstraat.getHuisnummer());
            Vermeulenstraat.setHuisnummer("500");
            adao.update(Vermeulenstraat);
            System.out.println("Na de update: " + Vermeulenstraat.getHuisnummer());

            //Een adres vinden op basis van een reiziger
            Reiziger groot = rdao.findByID(1);
            System.out.println("\n [Test] Verwachte resultaat van findByReiziger();");
            System.out.println(groot);
            Adres testFindReziger =  adao.findByReiziger(groot);
            System.out.println(testFindReziger);

            System.out.println("\n---------- GESLAAGD -------------");


            System.out.println("\n---------- Test Data.ReizigerDAO -------------");

            // Haal alle reizigers op uit de database
            List<Reiziger> reizigers = rdao.findAll();
            System.out.println("[Test] Data.ReizigerDAO.findAll() geeft de volgende reizigers:");
            for (Reiziger r : reizigers) {
                System.out.println(r);
            }
            System.out.println();

            // Delete een reiziger uit de database
            String gbdatum = "1981-03-14";

            Reiziger sietske = new Reiziger(43, "S", "", "uit", Date.valueOf(gbdatum));

            //Slaat een reiziger op in de database
            System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na Data.ReizigerDAO.save() ");
            rdao.save(sietske);
            reizigers = rdao.findAll();
            System.out.println(reizigers.size() + " reizigers\n");



            System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na Data.ReizigerDAO.delete() ");
            rdao.delete(sietske);
            reizigers = rdao.findAll();
            System.out.println(reizigers.size() + " reizigers\n");



            //Update de naam van de reiziger
            System.out.println("[Test] Naam voor de update: " + sietske.getNaam());
            sietske.setAchternaam("Boom");
            rdao.update(sietske);
            System.out.println("Naam na de update: " + sietske.getNaam());


            System.out.println("\n---------- GESLAAGD -------------");

            System.out.println("\n---------- Test OVChipkaartDAO ----------");

            // Haal alle OvChipkaarten op uit de database
            List<OVChipkaart> kaarten = ovdao.findAll();
            System.out.println("[Test] Ovkaart.findAll() geeft de volgende adressen:");
            for(OVChipkaart a : kaarten) {
                System.out.println(a);
            };

            //Test verwijder een OVkaart
            Reiziger piccardo = rdao.findByID(5);
            OVChipkaart kaart = new OVChipkaart(9999, Date.valueOf("2030-12-31"), 1, 50.0, piccardo);
            ovdao.save(kaart);
            piccardo.getOvchipkaarten().add(kaart);
            kaarten = ovdao.findAll();
            System.out.print("\n[Test] Eerst " + kaarten.size() + " kaarten na de OvDao.delete() ");
            ovdao.delete(kaart);
            kaarten = ovdao.findAll();
            System.out.println(kaarten.size() + " kaarten\n");



            System.out.println(piccardo);
            //Test save ovKaart
            System.out.print("\n[Test] Eerst " + kaarten.size() + " adressen na de OvDao.save() ");
            ovdao.save(kaart);
            kaarten = ovdao.findAll();
            System.out.println(kaarten.size() + " kaarten\n");


            // Update een ovKaart
            System.out.println("\n[Test] Saldo voor de ovdao.update(): " + kaart.getSaldo());
            kaart.setSaldo(1.0);
            ovdao.update(kaart);
            System.out.println("Na de update: " + kaart.getSaldo());


            // Find de kaarten van een reiziger
            System.out.println("\n [Test] Verwachte resultaat van findByReiziger() is 3 kaarten");
            Reiziger test = rdao.findByID(2);
            List<OVChipkaart> persoonlijkeKaarten = ovdao.findByReiziger(test);
            for(OVChipkaart a : persoonlijkeKaarten) {
                System.out.println(a);
            };

            ovdao.delete(kaart);

            System.out.println("\n---------- GESLAAGD -------------");

            System.out.println("\n---------- Test ProductDAOPsql ----------");

            List<Product> producten = pdao.findAll();
            System.out.println("[Test] Ovkaart.findAll() geeft de volgende adressen:");
            for(Product a : producten) {
                System.out.println(a);
            };

            OVChipkaart kaartje = new OVChipkaart(18326, Date.valueOf("2017-12-31"), 2, 00.0, piccardo);
            Product p = new Product(45, "Student", "Voor student", 65);
            kaartje.addProduct(p);
            p.addKaart(kaartje);
            producten = pdao.findAll();

            System.out.print("\n[Test] Eerst " + producten.size() + " kaarten na de pdao.delete() ");
            pdao.delete(p);
            producten = pdao.findAll();
            System.out.println(producten.size() + " kaarten\n");

            System.out.print("\n[Test] Eerst " + producten.size() + " kaarten na de pdao.save() ");
            pdao.save(p);
            producten = pdao.findAll();
            System.out.println(producten.size() + " kaarten\n");
            System.out.println(p);


            System.out.println("\n[Test] Saldo voor de pdao.update(): " + p.getBeschrijving());
            p.setBeschrijving("Voor studenten en oudere");
            pdao.update(p);
            System.out.println("Na de update: " + p.getBeschrijving());

            Reiziger test2 = rdao.findByID(2);
            OVChipkaart ovKaart = new OVChipkaart(35283, Date.valueOf("2018-05-31"), 2, 25.50, test2);


            System.out.println("\n [Test] Verwachte resultaat van findByOVChipkaart() is 3 kaarten");
            List<Product> resultaten = pdao.findByOVChipkaart(ovKaart);
            for(Product a : resultaten) {
                System.out.println(a);
            };

        }
    }

