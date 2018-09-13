package projektovanje.dbDAO;

import projektovanje.dto.DTOOprema;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOOprema implements IDBDAO{
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoOprema) {
        return null;
    }

    @Override
    public List<DTOOprema> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoOprema) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoOprema) {
        return null;
    }

    @Override
    public List<DTOOprema> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOOprema> ispisi() {
        return null;
    }
}
