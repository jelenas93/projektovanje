package projektovanje.bin.transakcije;

import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.zaposleni.Zaposleni;

import java.util.Date;

public class UlazaFaktura {
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
