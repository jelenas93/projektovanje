package projektovanje.dbDAO;

import projektovanje.dto.DTOFilm;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAOFilm implements IDBDAO {

    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoFilm) {
        return null;
    }

    @Override
    public List<DTOFilm> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoFilm) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoFilm) {
        return null;
    }

    @Override
    public List<DTOFilm> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTOFilm> ispisi() {
        return null;
    }
}
