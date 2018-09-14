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
    public Boolean azurirajBazu(List<? extends IDTO> dtoProdavacHraneIPica, Connection konekcijaNaBazu) {
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
