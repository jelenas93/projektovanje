package projektovanje.bin.film;

import java.io.Serializable;

public class Zanr implements Serializable {
    public static final long serialVersionUID=9003l;
    private Integer idZanra;
    private String nazivZanra;

    public Zanr() {
    }

    public Zanr(Integer idZanra, String nazivZanra) {
        this.idZanra = idZanra;
        this.nazivZanra = nazivZanra;
    }

    public Integer getIdZanra() {
        return idZanra;
    }

    public void setIdZanra(Integer idZanra) {
        this.idZanra = idZanra;
    }

    public String getNazivZanra() {
        return nazivZanra;
    }

    public void setNazivZanra(String nazivZanra) {
        this.nazivZanra = nazivZanra;
    }

    @Override
    public String toString() {
        return "Zanr{" +
                "idZanra=" + idZanra +
                ", nazivZanra='" + nazivZanra + '\'' +
                '}';
    }
}
