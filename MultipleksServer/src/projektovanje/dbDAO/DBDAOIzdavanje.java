package projektovanje.dbDAO;

import projektovanje.dto.DTOIzdavanje;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOIzdavanje implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
