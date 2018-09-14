package projektovanje.bin.film;

import projektovanje.bin.zaposleni.Zaposleni;

import java.io.Serializable;
import java.util.List;

public class Film implements Serializable {
    private Integer idFilma;
    private Zaposleni zaposleni;
    private List<Zanr> zanrovi;
    private Integer trajanje;
    private String naziv;
    private String opis;
    private String linkTrailera;
    private String tipFilma; //2D ili 3D

    public Film() {
    }

    public Film(Integer idFilma, Zaposleni zaposleni, String naziv, Integer trajanje,  String opis, String linkTrailera, String tipFilma ) {
        this.idFilma = idFilma;
        this.zaposleni = zaposleni;
        this.trajanje = trajanje;
        this.naziv = naziv;
        this.opis = opis;
        this.linkTrailera = linkTrailera;
        this.tipFilma = tipFilma;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public List<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(List<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

    public Integer getIdFilma() {
        return idFilma;
    }

    public void setIdFilma(Integer idFilma) {
        this.idFilma = idFilma;
    }

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getLinkTrailera() {
        return linkTrailera;
    }

    public void setLinkTrailera(String linkTrailera) {
        this.linkTrailera = linkTrailera;
    }

    public String getTipFilma() {
        return tipFilma;
    }

    public void setTipFilma(String tipFilma) {
        this.tipFilma = tipFilma;
    }
}
