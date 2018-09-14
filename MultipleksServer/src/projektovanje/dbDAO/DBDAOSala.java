package projektovanje.dbDAO;

import projektovanje.dto.DTOSala;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOSala implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOSala> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoSala, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOSala> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOSala> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
