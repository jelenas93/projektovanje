package projektovanje.bin.sala;

public class Sala {
    private Integer idSale;
    private Integer brojSjedista;

    public Sala() {
    }

    public Sala(Integer idSale, Integer brojSjedista) {
        this.idSale = idSale;
        this.brojSjedista = brojSjedista;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public Integer getBrojSjedista() {
        return brojSjedista;
    }

    public void setBrojSjedista(Integer brojSjedista) {
        this.brojSjedista = brojSjedista;
    }
}
