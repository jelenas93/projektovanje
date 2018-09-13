package projektovanje.dbDAO;

import projektovanje.dto.DTOSjediste;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOSjediste implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoSjediste) {
        return null;
    }

    @Override
    public List<DTOSjediste> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> list) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> list) {
        return null;
    }

    @Override
    public List<DTOSjediste> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOSjediste> ispisi() {
        return null;
    }
}
