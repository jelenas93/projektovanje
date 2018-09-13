package projektovanje.bin.sala;

import java.io.Serializable;

public class Sjediste  implements Serializable {
    private Integer idSjedista;
    private Sala sala;
    private Integer vrsta;
    private Integer kolona;

    public Sjediste() {
    }

    public Sjediste(Integer idSjedista, Sala sala, Integer vrsta, Integer kolona) {
        this.idSjedista = idSjedista;
        this.sala = sala;
        this.vrsta = vrsta;
        this.kolona = kolona;
    }

    public Integer getIdSjedista() {
        return idSjedista;
    }

    public void setIdSjedista(Integer idSjedista) {
        this.idSjedista = idSjedista;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Integer getVrsta() {
        return vrsta;
    }

    public void setVrsta(Integer vrsta) {
        this.vrsta = vrsta;
    }

    public Integer getKolona() {
        return kolona;
    }

    public void setKolona(Integer kolona) {
        this.kolona = kolona;
    }
}
