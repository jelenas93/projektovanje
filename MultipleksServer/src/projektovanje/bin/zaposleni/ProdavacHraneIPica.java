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

    public ProdavacHraneIPica(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idProdavacaHraneIPica) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
    }

    public ProdavacHraneIPica(Integer idProdavacaHraneIPica) {
        this.setIdZaposlenog(idProdavacaHraneIPica);
    }

    public ProdavacHraneIPica() {
    }
}
