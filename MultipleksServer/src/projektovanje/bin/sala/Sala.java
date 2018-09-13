package projektovanje.bin.sala;

import java.io.Serializable;

public class Sala  implements Serializable {
    private Integer idSale;
    private Integer brojVrsta;
    private Integer brojKolona;

    public Sala() {
    }

    public Sala(Integer idSale, Integer brojVrsta, Integer brojKolona) {
        this.idSale = idSale;
        this.brojVrsta = brojVrsta;
	this.brojKolona = brojKolona;
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
