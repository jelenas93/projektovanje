package projektovanje.dbDAO;

import projektovanje.dto.DTOKarta;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOKarta implements IDBDAO {

    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoKarta) {
        return null;
    }

    @Override
    public List<DTOKarta> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoKarta) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoKarta) {
        return null;
    }

    @Override
    public List<DTOKarta> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOKarta> ispisi() {
        return null;
    }
}
