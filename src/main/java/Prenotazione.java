import javax.persistence.*;
@Entity
@Table(name = "prenotazione")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utente_id",referencedColumnName = "id")
    private Utente utenteID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spettacolo_id",referencedColumnName = "id")
    private Spettacolo spettacoloID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posto_id",nullable = false,referencedColumnName = "id")
    private Posto postoID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtenteID() {
        return utenteID;
    }

    public void setUtenteID(Utente utenteID) {
        this.utenteID = utenteID;
    }

    public Spettacolo getSpettacoloID() {
        return spettacoloID;
    }

    public void setSpettacoloID(Spettacolo spettacoloID) {
        this.spettacoloID = spettacoloID;
    }

    public Posto getPostoID() {
        return postoID;
    }

    public void setPostoID(Posto postoID) {
        this.postoID = postoID;
    }
}

