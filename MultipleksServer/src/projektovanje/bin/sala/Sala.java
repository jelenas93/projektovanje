package projektovanje.bin.sala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sala  implements Serializable {
    public static final long serialVersionUID=9013l;
    private Integer idSale;
    private Integer brojVrsta;
    private Integer brojKolona;
    private List<Sjediste> sjedista;

    public Sala() {
    }

    @Override
    public String toString() {
        String ret="";
        Iterator<Sjediste> it = sjedista.iterator();
        while(it.hasNext()){
            ret+=it.next().toString();
        }
        return "Sala{" +
                "idSale=" + idSale +
                ", brojVrsta=" + brojVrsta +
                ", brojKolona=" + brojKolona +
                ", sjedista=" + ret +
                '}';
    }

    public Sala(Integer id){
        idSale = id;
    }

    public Sala(Integer idSale, Integer brojVrsta, Integer brojKolona, List<Sjediste> sjedista) {
        this.idSale = idSale;
        this.brojVrsta = brojVrsta;
	    this.brojKolona = brojKolona;
	    this.sjedista = sjedista;
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
        return brojKolona;
    }

    public void setBrojKolona(Integer brojKolona) {
        this.brojKolona = brojKolona;
    }
}
