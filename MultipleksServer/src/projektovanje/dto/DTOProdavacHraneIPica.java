package projektovanje.dto;

import projektovanje.bin.zaposleni.ProdavacHraneIPica;

import java.io.Serializable;

public class DTOProdavacHraneIPica implements Serializable {
    ProdavacHraneIPica prodavacHraneIPica;

    public DTOProdavacHraneIPica() {
    }

    public DTOProdavacHraneIPica(ProdavacHraneIPica prodavacHraneIPica) {
        this.prodavacHraneIPica = prodavacHraneIPica;
    }

    public ProdavacHraneIPica getProdavacHraneIPica() {
        return prodavacHraneIPica;
    }

    public void setProdavacHraneIPica(ProdavacHraneIPica prodavacHraneIPica) {
        this.prodavacHraneIPica = prodavacHraneIPica;
    }
}
