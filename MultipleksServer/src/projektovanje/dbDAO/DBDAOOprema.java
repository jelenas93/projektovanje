package projektovanje.dbDAO;

import projektovanje.dto.DTOOprema;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBDAOOprema implements IDBDAO{
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOOprema> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        return null;
    }


    @Override
    public List<DTOOprema> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOOprema> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
