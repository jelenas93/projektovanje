package projektovanje.dto;

import projektovanje.bin.transakcije.UlaznaFaktura;

import java.io.Serializable;

public class DTOUlaznaFaktura implements Serializable, IDTO {
    public static final long serialVersionUID=10022l;
    UlaznaFaktura ulaznaFaktura;

    public DTOUlaznaFaktura() {
    }

    public DTOUlaznaFaktura(UlaznaFaktura ulaznaFaktura) {
        this.ulaznaFaktura = ulaznaFaktura;
    }

    public UlaznaFaktura getUlaznaFaktura() {
        return ulaznaFaktura;
    }

    public void setUlaznaFaktura(UlaznaFaktura ulaznaFaktura) {
        this.ulaznaFaktura = ulaznaFaktura;
    }
}
