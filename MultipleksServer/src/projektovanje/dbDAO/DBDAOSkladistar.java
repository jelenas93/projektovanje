package projektovanje.dbDAO;

import projektovanje.dto.DTOSkladistar;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOSkladistar implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoSkladistar) {
        return null;
    }

    @Override
    public List<DTOSkladistar> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoSkladistar) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoSkladistar) {
        return null;
    }

    @Override
    public List<DTOSkladistar> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOSkladistar> ispisi() {
        return null;
    }
}
