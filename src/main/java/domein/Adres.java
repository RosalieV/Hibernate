package domein;

import java.sql.*;
import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adres_id")
    private int id;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "huisnummer")
    private String huisnummer;

    @Column(name = "straat")
    private String straat;

    @Column(name = "woonplaats")
    private String woonplaats;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public Adres() {
    }

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
         this.reiziger = reiziger;
    }

    public int getId() {
        return id;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setHuisnummer(String huisnummer){ this.huisnummer = huisnummer;}

    public int getReizerID(){return reiziger.getId();}

    public String toString() {
        String s = "Domein.domein.Reiziger {" + this.reiziger + ", Domein.domein.Adres {#" + this.id + " " + this.postcode +"-" + this.huisnummer +"}}";
        return s;
    }
}

