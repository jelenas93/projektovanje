package projektovanje.dbDAO;

import projektovanje.dto.DTOStavka;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOStavka implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoStavka) {
        return null;
    }

    @Override
    public List<DTOStavka> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoStavka) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoStavka) {
        return null;
    }

    @Override
    public List<DTOStavka> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOStavka> ispisi() {
        return null;
    }
}
