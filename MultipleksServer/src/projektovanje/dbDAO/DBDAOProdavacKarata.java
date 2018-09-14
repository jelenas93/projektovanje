package projektovanje.dbDAO;

import projektovanje.dto.DTOProdavacKarata;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBDAOProdavacKarata implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOProdavacKarata> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
<<<<<<< HEAD
=======
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        return null;
    }

    @Override
    public List<DTOProdavacKarata> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOProdavacKarata> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
