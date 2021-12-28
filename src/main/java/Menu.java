import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;


public class Menu {
    public void generaMenu(EntityManager manager) {
        boolean esci = false;
        while (!esci) {
            Scanner sc = new Scanner(System.in);
            Opzioni o = Opzioni.fromString(sc.nextLine());
            switch (o) {
                case REGISTRA:
                    manager.getTransaction().begin();
                    Utente utente = new Utente();
                    utente.genera();
                    manager.persist(utente);
                    manager.getTransaction().commit();
                    break;
                case PRENOTA:
                    // Utente(preso dal database)
                    System.out.println("Email : ");
                    String emailInInput = sc.nextLine();
                    manager.getTransaction().begin();
                    String qry = "SELECT u FROM Utente u WHERE email = '" + emailInInput + "'";
                    Utente utentePrenota = manager.createQuery(qry, Utente.class).getSingleResult();
                    System.out.println(utentePrenota.getNome());
                    manager.getTransaction().commit();

                    // spettacolo preso da database
                    System.out.println("Spettacolo : ");
                    String titoloInput = sc.nextLine();
                    manager.getTransaction().begin();
                    String qry2 = "SELECT u FROM Spettacolo u WHERE titolo = '" + titoloInput + "'";
                    Spettacolo spettacoloPrenota = manager.createQuery(qry2, Spettacolo.class).getSingleResult();
                    manager.getTransaction().commit();


                    // posto se è libero
                    manager.getTransaction().begin();
                    //Prendo tutti i posti liberi per lo spettacolo con titolo fornito dall'utente
                    //TBD: WHERE occumpato = false AND titolo = titolo in input
                    String qry3 = "SELECT u FROM Posto u WHERE salaID =" + spettacoloPrenota.salaID.getId() + "AND occupato=false";
                    // aggiungere sala alla query
                /* Creo una mappa <Sala, List<Posto>> dal result set della query. Nella mappa ho tutti i posti
                    tornati dalla select, raggruppati per sala */
                    //HashMap<Sala,Posto> posto = new HashMap<Sala,Posto>();

                    //Stampo all'utente tutte le sale con posti liberi (le chiavi della mappa)
                    //Chiedo all'utente di scegliere la sala per id
                    //Prenoto un posto nella sala con id indicato dall'utente
                    Posto posto = manager.createQuery(qry3, Posto.class).getResultList().get(0);
                    posto.setOccupato(true);
                    manager.persist(posto);
                    manager.getTransaction().commit();


                    // Genera una prenotazione
                    Prenotazione prenotazione = new Prenotazione();
                    prenotazione.setPostoID(posto);
                    prenotazione.setSpettacoloID(spettacoloPrenota);
                    prenotazione.setUtenteID(utentePrenota);

                    manager.getTransaction().begin();
                    manager.persist(prenotazione);
                    manager.getTransaction().commit();


                    System.out.println("Grazie della prenotazione " + utentePrenota.getNome());
                    System.out.println("Hai prenotato il posto : " + posto.getPosto() + " alla fila " + posto.getFila());
                    System.out.println(prenotazione.getSpettacoloID().getPrezzo() + " €");


                    break;
                case RICERCA:
                    //QUERY where lista spettacoli per città e data
                    System.out.println("Inserisci città: ");
                    String cittaInput = sc.nextLine();


                    System.out.println("Inserisci data: ");
                    String anno = sc.nextLine();
                    String mese = sc.nextLine();
                    String giorno = sc.nextLine();

                    manager.getTransaction().begin();

                    String qry4 = "SELECT sp.titolo FROM Sede se FULL JOIN Sala sa ON se.id = sa.sedeID FULL JOIN Spettacolo sp ON sa.id = sp.salaID WHERE se.citta = '" + cittaInput + "' AND sp.orario='" + anno + "-" + mese + "-" + giorno + "'";

                    List<Object[]> spettacoloRicerca = manager.createQuery(qry4).getResultList();
                    manager.getTransaction().commit();

                    for (Object s : spettacoloRicerca) {
                        System.out.println(s);
                    }
                    break;

                case SUGGERIMENTI:
                    System.out.println("Email : ");
                    String emailInInput1 = sc.nextLine();
                    manager.getTransaction().begin();
                    String qry1 = "SELECT u FROM Utente u WHERE email = '" + emailInInput1 + "'";
                    Utente utente1 = manager.createQuery(qry1, Utente.class).getSingleResult();
                    // prendere gli ultimi 3 generi, puoi con una select * from spettacolo where genere IN (select genere from spettacolo where id in (id sp1,id sp2, id sp3).
                    List<Prenotazione> prenotazioneList = utente1.getPrenotazioni();
                    for (Prenotazione p : prenotazioneList) {
                        System.out.println(p.getSpettacoloID().getId());
                    }
                    int numeroPrenotazioni = prenotazioneList.size();

                    //3 controlli sui idSpettacolo
                    int idSpettacolo1 = -1;
                    if (numeroPrenotazioni >= 3) {
                        idSpettacolo1 = prenotazioneList.get(2).getSpettacoloID().getId();
                    }
                    int idSpettacolo2 = -1;
                    if (numeroPrenotazioni >= 2) {
                        idSpettacolo2 = prenotazioneList.get(1).getSpettacoloID().getId();
                    }
                    int idSpettacolo3 = -1;
                    if (numeroPrenotazioni >= 1) {
                        idSpettacolo3 = prenotazioneList.get(0).getSpettacoloID().getId();

                    }
                    manager.getTransaction().commit();

                    manager.getTransaction().begin();
                    String qry5 = "SELECT u FROM Spettacolo u WHERE genere IN (SELECT genere from Spettacolo WHERE id IN ('" + idSpettacolo3 + "','" + idSpettacolo2 + "','" + idSpettacolo1 + "'))";
                    List<Spettacolo> ultimiSpettacoli = manager.createQuery(qry5).getResultList();

                    manager.getTransaction().commit();

                    for (Prenotazione p1 : utente1.getPrenotazioni()) {
                        ultimiSpettacoli.remove(p1.getSpettacoloID());
                    }

                    LocalDateTime adesso = LocalDateTime.now();

                    Iterator<Spettacolo> iter = ultimiSpettacoli.iterator();
                    while (iter.hasNext()) {
                        Spettacolo s = iter.next();

                        if (s.getOrario().isBefore(adesso.minusDays(30)))
                            iter.remove();
                    }
                    for (Spettacolo s : ultimiSpettacoli) {
                        System.out.println(s.getTitolo());
                    }


                case ESCI:
                    esci = true;
                    break;


            }
        }
    }
}
