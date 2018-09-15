package projektovanje.bin.oprema;

import projektovanje.bin.zaposleni.Zaposleni;

public class Artikal implements IOprema{
    private Double jedinicnaCijena;
    private Integer idArtikla;
    private String naziv;
    private Integer kolicinaNaStanju;
    private String tip;
    private String barKod;
    private Zaposleni zaposleni;

    public Artikal() {
    }

    public Artikal(Integer id){
        idArtikla = id;
    }

    public Artikal(Integer idArtikla, String naziv, Integer kolicinaNaStanju, Double jedinicnaCijena,  String tip, String barKod, Zaposleni zaposleni) {
        this.jedinicnaCijena = jedinicnaCijena;
        this.idArtikla = idArtikla;
        this.naziv = naziv;
        this.kolicinaNaStanju = kolicinaNaStanju;
        this.tip = tip;
        this.barKod = barKod;
        this.zaposleni = zaposleni;
    }

    public Double getJedinicnaCijena() {
        return jedinicnaCijena;
    }

    public void setJedinicnaCijena(Double jedinicnaCijena) {
        this.jedinicnaCijena = jedinicnaCijena;
    }

    public Integer getIdArtikla() {
        return idArtikla;
    }

    public void setIdArtikla(Integer idArtikla) {
        this.idArtikla = idArtikla;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(Integer kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getBarKod() {
        return barKod;
    }

    public void setBarKod(String barKod) {
        this.barKod = barKod;
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

    @Override
    public String vratiTipOpreme() {
        return this.naziv + "#ARTIKAL";
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "jedinicnaCijena=" + jedinicnaCijena +
                ", idArtikla=" + idArtikla +
                ", naziv='" + naziv + '\'' +
                ", kolicinaNaStanju=" + kolicinaNaStanju +
                ", tip='" + tip + '\'' +
                ", barKod='" + barKod + '\'' +
                '}';
    }
}
