package projektovanje.dbDAO;

import projektovanje.dto.DTORepertoar;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAORepertoar implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTORepertoar> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoRepertoar, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTORepertoar> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTORepertoar> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
