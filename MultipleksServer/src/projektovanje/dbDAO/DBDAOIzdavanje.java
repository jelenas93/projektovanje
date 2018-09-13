package projektovanje.dbDAO;

import projektovanje.dto.DTOIzdavanje;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOIzdavanje implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoIzdavanje) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoIzdavanje) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoIzdavanje) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOIzdavanje> ispisi() {
        return null;
    }
}
