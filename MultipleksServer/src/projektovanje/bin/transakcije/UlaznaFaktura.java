package projektovanje.bin.transakcije;

import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.Date;

public class UlaznaFaktura implements Serializable {
    private Integer idFakute;
    private Zaposleni zaposleni;
    private String brojRacina;
    private String vrstaTransakcije;
    private Double kolicina;
    private String jedinicaMjere;
    private Double cijena;
    private String kupac;
    private Date datum;
    private IOprema kupljenaRoba;
}
