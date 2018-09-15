package projektovanje.dbDAO;

import projektovanje.dto.DTOKinooperater;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBDAOKinooperater implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOKinooperater> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        return null;
    }


    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOKinooperater> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
