package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Menadzer extends Zaposleni {
    private Integer idMenadzera;

    public Integer getIdMenadzera() {
        return idMenadzera;
    }

    public void setIdMenadzera(Integer idMenadzera) {
        this.idMenadzera = idMenadzera;
    }

    public Menadzer(Integer idMenadzera) {
        this.idMenadzera = idMenadzera;
    }

    public Menadzer() {
    }

    public Menadzer(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }

    public Menadzer(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idMenadzera) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idMenadzera = idMenadzera;
    }
}
