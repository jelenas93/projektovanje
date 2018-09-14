package projektovanje.dbDAO;

import projektovanje.dto.DTOArtikal;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOArtikal implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOArtikal> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoArtikal, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOArtikal> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOArtikal> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
