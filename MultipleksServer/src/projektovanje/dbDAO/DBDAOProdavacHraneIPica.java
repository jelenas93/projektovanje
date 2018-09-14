package projektovanje.dbDAO;

import projektovanje.dto.DTOProdavacHraneIPica;
import projektovanje.dto.IDTO;

import java.sql.Connection;
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
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO dtoProdavacHraneIPica, Connection konekcijaNaBazu) {
=======
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
        return null;
    }

    @Override
    public List<DTOProdavacHraneIPica> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOProdavacHraneIPica> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
