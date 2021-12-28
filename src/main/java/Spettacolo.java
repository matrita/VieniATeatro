import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "spettacolo")
public class Spettacolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="titolo" ,nullable = false, length=50)
    String titolo;
    @Column(name="genere" ,nullable = false, length=50)
    String genere;
    @Column(name="orario" ,nullable = false, length=50)
    LocalDateTime orario;
    @Column(name="prezzo" ,nullable = false, length=50)
    Double prezzo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sala_id",nullable = false,referencedColumnName = "id")
    Sala salaID;
    @OneToMany(mappedBy="spettacoloID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prenotazione> prenotazioni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public LocalDateTime getOrario() {
        return orario;
    }

    public void setOrario(LocalDateTime orario) {
        this.orario = orario;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Sala getSalaID() {
        return salaID;
    }

    public void setSalaID(Sala salaID) {
        this.salaID = salaID;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}

