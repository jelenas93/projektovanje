package projektovanje.dbDAO;

import projektovanje.dto.DTONalog;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAONalog implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoNalog) {
        return null;
    }

    @Override
    public List<DTONalog> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoNalog) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoNalog) {
        return null;
    }

    @Override
    public List<DTONalog> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTONalog> ispisi() {
        return null;
    }
}
