package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class ProdavacKarata extends Zaposleni {
    private Integer idProdavcaKarata;

    public ProdavacKarata() {
    }

    public ProdavacKarata(Integer idProdavcaKarata) {
        this.idProdavcaKarata = idProdavcaKarata;
    }

    public ProdavacKarata(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
    }

    public ProdavacKarata(String ime, String prezime, String JMBG, Plata plata, Integer idZaposlenog, Nalog nalog, Integer idProdavcaKarata) {
        super(ime, prezime, JMBG, plata, idZaposlenog, nalog);
        this.idProdavcaKarata = idProdavcaKarata;
    }
}
