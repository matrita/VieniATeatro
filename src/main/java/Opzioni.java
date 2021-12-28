public enum Opzioni{
    REGISTRA ("registra"), PRENOTA ("prenota"),RICERCA("ricerca spettacolo"),SUGGERIMENTI("suggerimenti"), ESCI("esci");
    private String str;
    /**
     * Costruttore privato che costruisce l'enum da stringa
     * @param str
     */
    private Opzioni(String str)
    {
        this.str=str;
    }

    @Override
    public String toString()
    {
        return str;
    }
    /**
     * Metodo per poter costruire l'enum da Stringa
     * @param text
     * @return
     *
     */
    public static Opzioni fromString(String text) {
        if (text != null) {
            for (Opzioni c : Opzioni.values()) {
                if (text.equals(c.str)) {
                    return c;
                }
            }
        }
        return Opzioni.ESCI;
    }

}
