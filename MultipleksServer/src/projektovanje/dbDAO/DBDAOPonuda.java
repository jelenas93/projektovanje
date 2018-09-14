package projektovanje.dbDAO;

import projektovanje.dto.DTOPonuda;
import projektovanje.dto.IDTO;

import java.sql.Connection;
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
    public Boolean azurirajBazu(List<? extends IDTO> dtoPonuda, Connection konekcijaNaBazu) {
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
