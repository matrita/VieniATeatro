import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="nome" ,nullable = false, length=50)
    private String nome;
    @OneToMany(mappedBy="salaID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Posto> posti;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sede_id",referencedColumnName = "id")
    private Sede sedeID;
    @OneToMany(mappedBy="salaID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Spettacolo> spettacoli;

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


    public Sede getSedeID() {
        return sedeID;
    }

    public void setSedeID(Sede sedeID) {
        this.sedeID = sedeID;
    }

    public Set<Posto> getPosti() {
        return posti;
    }

    public void setPosti(Set<Posto> posti) {
        this.posti = posti;
    }

    public Set<Spettacolo> getSpettacoli() {
        return spettacoli;
    }

    public void setSpettacoli(Set<Spettacolo> spettacoli) {
        this.spettacoli = spettacoli;
    }
}
