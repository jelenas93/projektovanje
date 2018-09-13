package projektovanje.dbDAO;

import projektovanje.dto.DTOAdministrator;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOAdministrator implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoAdministrator) {
        return null;
    }

    @Override
    public List<DTOAdministrator> citajIzBaze() {/*
    Strin[] = select nesto o administratoru from tabela
    return new DTOAdministrator(string[0],])*/
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoAdministrator) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoAdministrator) {
        return null;
    }

    @Override
    public List<DTOAdministrator> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOAdministrator> ispisi() {
        return null;
    }
}
