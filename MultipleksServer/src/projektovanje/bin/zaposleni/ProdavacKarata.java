package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class ProdavacKarata extends Zaposleni {
    public static final long serialVersionUID=9021l;

    public ProdavacKarata() {
    }

    public ProdavacKarata(Integer idProdavcaKarata) {
        this.setIdZaposlenog(idProdavcaKarata);
    }

    public ProdavacKarata(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Boolean aktivan, Integer idProdavcaKarata) {
        super(idZaposlenog, plata, ime, prezime, JMBG, aktivan, nalog);
    }


    public ProdavacKarata(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(), zaposleni.getAktivan(), zaposleni.getNalog());
    }


    public void setIdProdavcaKarata(Integer idProdavcaKarata){
        this.setIdZaposlenog(idProdavcaKarata);
    }

    public Integer getIdProdavcaKarata(){
        return this.getIdZaposlenog();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
