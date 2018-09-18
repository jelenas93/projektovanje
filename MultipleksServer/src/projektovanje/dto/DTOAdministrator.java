package projektovanje.dto;

import projektovanje.bin.zaposleni.Administrator;

import java.io.Serializable;

public class DTOAdministrator implements Serializable, IDTO {
    public static final long serialVersionUID=10000l;
    Administrator administrator;

    public DTOAdministrator() {
    }

    public DTOAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
