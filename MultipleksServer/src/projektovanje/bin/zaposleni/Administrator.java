package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Administrator extends Zaposleni{

    public Integer getIdAdministratora() {
        return this.getIdZaposlenog();
    }

    public void setIdAdministratora(Integer idAdministratora) {
        this.setIdZaposlenog(idAdministratora);
    }

    public Administrator(Integer idAdministratora) {
        this.setIdZaposlenog(idAdministratora);
    }

    public Administrator() {
    }

    public Administrator(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(), zaposleni.getNalog());
    }

    public Administrator(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
    }
}
