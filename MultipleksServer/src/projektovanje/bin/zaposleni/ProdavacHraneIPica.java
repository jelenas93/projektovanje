package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class ProdavacHraneIPica extends Zaposleni {
    private Integer idProdavacaHraneIPica;

    public ProdavacHraneIPica(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }

    public ProdavacHraneIPica(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idProdavacaHraneIPica) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idProdavacaHraneIPica = idProdavacaHraneIPica;
    }

    public ProdavacHraneIPica(Integer idProdavacaHraneIPica) {
        this.idProdavacaHraneIPica = idProdavacaHraneIPica;
    }

    public ProdavacHraneIPica() {
    }
}
