package projektovanje.dto;

import projektovanje.bin.zaposleni.ProdavacKarata;

import java.io.Serializable;

public class DTOProdavacKarata implements Serializable, IDTO {
    ProdavacKarata prodavacKarata;

    public DTOProdavacKarata() {
    }

    public DTOProdavacKarata(ProdavacKarata prodavacKarata) {
        this.prodavacKarata = prodavacKarata;
    }

    public ProdavacKarata getProdavacKarata() {
        return prodavacKarata;
    }

    public void setProdavacKarata(ProdavacKarata prodavacKarata) {
        this.prodavacKarata = prodavacKarata;
    }
}
