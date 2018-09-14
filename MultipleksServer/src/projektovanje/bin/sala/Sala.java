package projektovanje.bin.sala;

import java.io.Serializable;
import java.util.List;

public class Sala  implements Serializable {
    private Integer idSale;
    private Integer brojVrsta;
    private Integer brojKolona;
    private List<Sjediste> sjedista;

    public Sala() {
    }

    public Sala(Integer idSale, Integer brojVrsta, Integer brojKolona, List<Sjediste> sjedista) {
        this.idSale = idSale;
        this.brojVrsta = brojVrsta;
	this.brojKolona = brojKolona;
    }

    public List<Sjediste> getSjedista() {
        return sjedista;
    }

    public void setSjedista(List<Sjediste> sjedista) {
        this.sjedista = sjedista;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public Integer getBrojVrsta() {
        return brojVrsta;
    }

    public void setBrojVrsta(Integer brojVrsta) {
        this.brojVrsta = brojVrsta;
    }

    public Integer getBrojKolona() {
        return brojVrsta;
    }

    public void setBrojKolona(Integer brojKolona) {
        this.brojVrsta = brojVrsta;
    }
}
