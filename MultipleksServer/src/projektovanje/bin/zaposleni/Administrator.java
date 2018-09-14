package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Administrator extends Zaposleni{
    private Integer idAdministratora;

    public Administrator(Integer idAdministratora) {
        this.idAdministratora = idAdministratora;
    }

    public Administrator() {
    }

    public Administrator(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idAdministratora) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idAdministratora = idAdministratora;
    }

    public Administrator(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }
}
