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

    public Menadzer(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idMenadzera) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idMenadzera = idMenadzera;
    }
}
