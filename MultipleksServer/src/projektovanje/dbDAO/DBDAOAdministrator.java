package projektovanje.dbDAO;

import projektovanje.dto.DTOAdministrator;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOAdministrator implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOAdministrator> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoAdministrator, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOAdministrator> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOAdministrator> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
