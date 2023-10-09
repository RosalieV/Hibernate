package domein;

import domein.OVChipkaart;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_nummer")
    private int productNummer;

    @Column(name = "naam")
    private String naam;

    @Column(name = "beschrijving")
    private String beschrijving;

    @Column(name = "prijs")
    private double prijs;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns = @JoinColumn(name = "product_nummer"),
            inverseJoinColumns = @JoinColumn(name = "kaart_nummer"))
    private List<OVChipkaart> ovChipkaarten;

    public Product(int pr, String nm, String bs, double prs) {
        this.productNummer = pr;
        this.naam = nm;
        this.beschrijving = bs;
        this.prijs = prs;
        this.ovChipkaarten = new ArrayList<>();
    }

    public Product() {

    }

    public int getProductNummer() {
        return productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void addKaart(OVChipkaart ov){
        ovChipkaarten.add(ov);
    }

    public void removeKaart(OVChipkaart ov){
        ovChipkaarten.remove(ov);
    }

    @Override
    public String toString() {
        return "domein.Product{" +
                "productNummer=" + productNummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", ovChipkaartenAantal=" + ovChipkaarten.size() +
                '}';
    }
}
