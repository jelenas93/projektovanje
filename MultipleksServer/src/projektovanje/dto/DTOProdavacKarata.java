package projektovanje.dto;

import projektovanje.bin.zaposleni.ProdavacKarata;

import java.io.Serializable;

public class DTOProdavacKarata implements Serializable, IDTO {
    public static final long serialVersionUID=10013l;
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
