package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Skladistar extends Zaposleni {
    public static final long serialVersionUID=9023l;

    public Integer getIdSkladistara() {
        return this.getIdZaposlenog();
    }

    public void setIdSkladistara(Integer idSkladistara) {
        this.setIdZaposlenog(idSkladistara);
    }

    public Skladistar(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog,Boolean aktivan, Integer idSkladistara) {
        super(idZaposlenog, plata, ime, prezime, JMBG, aktivan, nalog);
    }

    public Skladistar() {
    }


    public Skladistar(Zaposleni zaposleni){
        super(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), zaposleni.getIme(), zaposleni.getPrezime(), zaposleni.getJMBG(),zaposleni.getAktivan(), zaposleni.getNalog());
    }


    public Skladistar(Integer idSkladistara) {
        this.setIdZaposlenog(idSkladistara);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
