package projektovanje.bin.film;

import java.io.Serializable;

public class Film implements Serializable {
    private Integer idFilma;
    private Integer idZaposlenog;
    private Integer trajanje;
    private String naziv;
    private String opis;
    private String linkTrailera;
    private String tipFilma; //2D ili 3D

    public Film() {
    }

    public Film(Integer idFilma, Integer idZaposlenog, Integer trajanje, String naziv, String opis, String linkTrailera, String tipFilma) {
        this.idFilma = idFilma;
        this.idZaposlenog = idZaposlenog;
        this.trajanje = trajanje;
        this.naziv = naziv;
        this.opis = opis;
        this.linkTrailera = linkTrailera;
        this.tipFilma = tipFilma;
    }

    public Integer getIdFilma() {
        return idFilma;
    }

    public void setIdFilma(Integer idFilma) {
        this.idFilma = idFilma;
    }

    public Integer getIdZaposlenog() {
        return idZaposlenog;
    }

    public void setIdZaposlenog(Integer idZaposlenog) {
        this.idZaposlenog = idZaposlenog;
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
