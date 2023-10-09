package domein;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartNummer;

    @Column(name = "geldig_tot")
    private Date geldigTot;

    @Column(name = "klasse")
    private int klasse;

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    @ManyToMany(mappedBy = "ovChipkaarten")
    private List<Product> producten;


    public OVChipkaart(int kaart_nummer, java.sql.Date geldig_tot, int klasse, double saldo, Reiziger reiziger){
        this.kaartNummer = kaart_nummer;
        this.geldigTot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.producten = new ArrayList<>();
    }

    public OVChipkaart() {

    }

    public int getKaartNummer(){return kaartNummer;}
    public java.sql.Date getGeldigTot() {return (java.sql.Date) geldigTot;}
    public int getKlasse() {return klasse;}
    public double getSaldo() {return saldo;}
    public Reiziger getReiziger(){return reiziger;}
    public void setSaldo(double s){this.saldo = s;}
    public List<Product> getProducten() {return producten;}
    public void addProduct(Product product){producten.add(product);}
    public void removeProduct(Product product){producten.remove(product);}


    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaartNummer=" + kaartNummer +
                ", geldigTot=" + geldigTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reizigerID=" + reiziger.getId() +
                ", productenTotaal=" + producten.size() +
                '}';
    }
}
