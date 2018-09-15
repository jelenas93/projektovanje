package projektovanje.dbDAO;

import projektovanje.dto.DTOProdavacHraneIPica;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBDAOProdavacHraneIPica implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOProdavacHraneIPica> citajIzBaze(Connection konekcijaNaBazu) {
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
    public List<DTOProdavacHraneIPica> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
