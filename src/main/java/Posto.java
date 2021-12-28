import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "posto")
public class Posto {
    @Column(name="fila" ,nullable = false, length=50)
    char fila;
    @Column(name="posto" ,nullable = false, length=50)
    int posto;
    @Column(name="occupato" ,nullable = false, length=50,columnDefinition = "boolean default false")
    boolean occupato;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prenotazione_id",referencedColumnName = "id")
    Prenotazione prenotazioneID;
     */
    //errore non serve

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sala_id",referencedColumnName = "id",nullable = false)
    Sala salaID;

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
    }

    public int getPosto() {
        return posto;
    }

    public void setPosto(int posto) {
        this.posto = posto;
    }

    public boolean isOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Sala getSalaID() {
        return salaID;
    }

    public void setSalaID(Sala salaID) {
        this.salaID = salaID;
    }
}

