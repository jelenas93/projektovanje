package projektovanje.dbDAO;

import projektovanje.dto.DTONalog;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAONalog implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTONalog> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTONalog> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTONalog> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
