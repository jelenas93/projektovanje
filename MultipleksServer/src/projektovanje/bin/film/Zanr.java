package projektovanje.bin.film;

public class Zanr {
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
}
