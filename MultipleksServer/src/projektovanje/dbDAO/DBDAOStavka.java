package projektovanje.dbDAO;

import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOStavka implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<? extends IDTO> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
