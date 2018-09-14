package projektovanje.dbDAO;

import projektovanje.dto.DTOFilm;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOFilm implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOFilm> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoFilm, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOFilm> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOFilm> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
