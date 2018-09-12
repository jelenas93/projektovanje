package projektovanje.bin.oprema;

import projektovanje.bin.zaposleni.Zaposleni;

public class Oprema implements IOprema{
    private Integer idOpreme;
    private Integer brojInventara;
    private String naziv;
    private Boolean ispravnost;
    private Zaposleni zaposleni;

    public Oprema() {
    }

    public Oprema(Integer idOpreme, Integer brojInventara, String naziv, Boolean ispravnost, Zaposleni zaposleni) {
        this.idOpreme = idOpreme;
        this.brojInventara = brojInventara;
        this.naziv = naziv;
        this.ispravnost = ispravnost;
        this.zaposleni = zaposleni;
    }

    public Integer getIdOpreme() {
        return idOpreme;
    }

    public void setIdOpreme(Integer idOpreme) {
        this.idOpreme = idOpreme;
    }

    public Integer getBrojInventara() {
        return brojInventara;
    }

    public void setBrojInventara(Integer brojInventara) {
        this.brojInventara = brojInventara;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Boolean getIspravnost() {
        return ispravnost;
    }

    public void setIspravnost(Boolean ispravnost) {
        this.ispravnost = ispravnost;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public Integer getIdZaposlenog(){
        return this.zaposleni.getIdZaposlenog();
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
