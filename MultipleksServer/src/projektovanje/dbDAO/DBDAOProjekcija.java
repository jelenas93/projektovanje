package projektovanje.dbDAO;

import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOProjekcija implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOProjekcija> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoProjekcija, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOProjekcija> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOProjekcija> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
