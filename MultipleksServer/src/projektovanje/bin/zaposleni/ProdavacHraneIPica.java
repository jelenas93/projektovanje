package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class ProdavacHraneIPica extends Zaposleni {
    private Integer idProdavacaHraneIPica;

    public Integer getIdProdavacaHraneIPica() {
        return idProdavacaHraneIPica;
    }

    public void setIdProdavacaHraneIPica(Integer idProdavacaHraneIPica) {
        this.idProdavacaHraneIPica = idProdavacaHraneIPica;
    }

    public ProdavacHraneIPica(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idProdavacaHraneIPica) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idProdavacaHraneIPica = idProdavacaHraneIPica;
    }

    public ProdavacHraneIPica(Integer idProdavacaHraneIPica) {
        this.idProdavacaHraneIPica = idProdavacaHraneIPica;
    }

    public ProdavacHraneIPica() {
    }
}
