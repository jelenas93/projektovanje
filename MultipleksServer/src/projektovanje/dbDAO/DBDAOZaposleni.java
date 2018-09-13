package projektovanje.dbDAO;

import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOZaposleni implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoZaposleni) {
        return null;
    }

    @Override
    public List<DTOZaposleni> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoZaposleni) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoZaposleni) {
        return null;
    }

    @Override
    public List<DTOZaposleni> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOZaposleni> ispisi() {
        return null;
    }
}
