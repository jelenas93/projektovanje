package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Administrator extends Zaposleni{
    private Integer idAdministratora;

    public Integer getIdAdministratora() {
        return idAdministratora;
    }

    public void setIdAdministratora(Integer idAdministratora) {
        this.idAdministratora = idAdministratora;
    }

    public Administrator(Integer idAdministratora) {
        this.idAdministratora = idAdministratora;
    }



    public Administrator() {
    }

    public Administrator(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idAdministratora) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idAdministratora = idAdministratora;
    }
}
