package domein;

import domein.Adres;
import domein.OVChipkaart;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reiziger_id")
    private int id;

    @Column(name = "voorletters")
    private String voorletters;

    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "geboortedatum")
    private Date geboortedatum;

    @ManyToOne
    @JoinColumn(name = "adres_id")
    private Adres adres;

    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> ovchipkaarten;


    public Reiziger(int rId, String voorl, String tussenv, String achternm, Date geboortedtm){
        id = rId;
        voorletters = voorl;
        tussenvoegsel = tussenv;
        achternaam = achternm;
        geboortedatum = geboortedtm;
        adres = null;
        this.ovchipkaarten = new ArrayList<OVChipkaart>();

    }

    public Reiziger() {

    }

    public int getId() {
        return id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setId(int nwId) {
        id = nwId;
    }

    public int getAdresID(){return adres.getId();}

    public Adres getAdres(){return adres;}

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getNaam() {
        String naam= voorletters + ". " + tussenvoegsel + " " + achternaam;
        if (tussenvoegsel == null) {
            naam = voorletters + ". " + achternaam;
        }
        return naam;
    }

    public List<OVChipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }



    @Override
    public String toString() {
        return "domein.Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                ", ovchipkaarten=" + ovchipkaarten +
                '}';
    }
}
