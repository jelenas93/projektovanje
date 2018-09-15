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

    public ProdavacKarata(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idProdavcaKarata) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        this.idProdavcaKarata = idProdavcaKarata;
    }
}
