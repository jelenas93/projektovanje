package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class ProdavacHraneIPica extends Zaposleni {

    public Integer getIdProdavacaHraneIPica() {
        return this.getIdZaposlenog();
    }

    public void setIdProdavacaHraneIPica(Integer idProdavacaHraneIPica) {
        this.setIdZaposlenog(idProdavacaHraneIPica);
    }

    public ProdavacHraneIPica(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Boolean aktivan, Nalog nalog) {
        super(idZaposlenog, plata, ime, prezime, JMBG, aktivan, nalog);
    }


    public ProdavacHraneIPica(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(), zaposleni.getAktivan(), zaposleni.getNalog());
    }


    public ProdavacHraneIPica(Integer idProdavacaHraneIPica) {
        this.setIdZaposlenog(idProdavacaHraneIPica);
    }

    public ProdavacHraneIPica() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
