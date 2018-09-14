package projektovanje.dbDAO;

import projektovanje.dto.DTOPonuda;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBDAOPonuda implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOPonuda> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO dtoPonuda, Connection konekcijaNaBazu) {
=======
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
        return null;
    }

    @Override
    public List<DTOPonuda> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOPonuda> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
