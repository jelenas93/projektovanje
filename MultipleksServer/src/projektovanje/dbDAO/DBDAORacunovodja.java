package projektovanje.dbDAO;

import projektovanje.dto.DTORacuovodja;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAORacunovodja implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTORacuovodja> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTORacuovodja> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTORacuovodja> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
