package projektovanje.dbDAO;

import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOProjekcija implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoProjekcija) {
        return null;
    }

    @Override
    public List<DTOProjekcija> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoProjekcija) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoProjekcija) {
        return null;
    }

    @Override
    public List<DTOProjekcija> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOProjekcija> ispisi() {
        return null;
    }
}
