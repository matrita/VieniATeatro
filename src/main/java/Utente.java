import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="nome" ,nullable = false, length=50)
    String nome;
    @Column(name="cognome" ,nullable = false, length=50)
    String cognome;
    @Column(name="residenza" ,nullable = false, length=50)
    String residenza;
    @Column(name="email",unique = true, nullable = false, length=50)
    String email;
    @Column(name="telefono",unique = true ,nullable = false, length=50)
    String telefono;
    @OneToMany(mappedBy="utenteID", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Prenotazione> prenotazioni=new LinkedList<>();


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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void genera(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci Nome");
        this.nome=sc.nextLine();
        System.out.println("Inserisci Cognome");
        this.cognome=sc.nextLine();
        System.out.println("Inserisci Residenza");
        this.residenza=sc.nextLine();
        System.out.println("Inserisci Email");
        this.email=sc.nextLine();
        System.out.println("Inserisci Telefono");
        this.telefono=sc.nextLine();
    }
/*
    public void addPrenotazione(Prenotazione prenotazione){
        if(prenotazioni == null){
            prenotazioni = new LinkedList<>();
        }
        ((LinkedList<Prenotazione>)prenotazioni).addLast(prenotazione); //cast serve da interfaccia o super a classe specifica , aggiungo prenotazione in fondo alla lista
    }

 */

}

