package projektovanje.dbDAO;

import projektovanje.dto.DTOKlijent;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBDAOKlijent implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOKlijent> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        return null;
    }


    @Override
    public List<DTOKlijent> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOKlijent> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
