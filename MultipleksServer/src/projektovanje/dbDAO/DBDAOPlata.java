package projektovanje.dbDAO;

import projektovanje.dto.DTOPlata;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOPlata implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOPlata> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO dtoPlata, Connection konekcijaNaBazu) {
=======
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
        return null;
    }

    @Override
    public List<DTOPlata> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOPlata> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
