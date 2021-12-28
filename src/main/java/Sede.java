import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sede")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="nome" ,nullable = false, length=50)
    String nome;
    @Column(name="citta" ,nullable = false, length=50)
    String citta;
    @Column(name="indirizzo" ,nullable = false, length=50)
    String indirizzo;
    @Column(name="numerosale" ,nullable = false, length=50)
    int numeroSale;
    @Column(name="apertochiuso" ,nullable = false, length=50,columnDefinition = "boolean default true")
    boolean apertoChiuso;
    @OneToMany(mappedBy="sedeID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sala> sale;

    public int getNumeroSale() {
        return numeroSale;
    }

    public void setNumeroSale(int numeroSale) {
        this.numeroSale = numeroSale;
    }

    public Set<Sala> getSale() {
        return sale;
    }

    public void setSale(Set<Sala> sale) {
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


    public boolean isApertoChiuso() {
        return apertoChiuso;
    }

    public void setApertoChiuso(boolean apertoChiuso) {
        this.apertoChiuso = apertoChiuso;
    }
}
