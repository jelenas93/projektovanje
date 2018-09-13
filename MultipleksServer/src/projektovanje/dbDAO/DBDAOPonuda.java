package projektovanje.dbDAO;

import projektovanje.dto.DTOPonuda;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOPonuda implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoPonuda) {
        return null;
    }

    @Override
    public List<DTOPonuda> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoPonuda) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoPonuda) {
        return null;
    }

    @Override
    public List<DTOPonuda> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOPonuda> ispisi() {
        return null;
    }
}
