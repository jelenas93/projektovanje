package projektovanje.dbDAO;

import projektovanje.dto.DTOProdavacKarata;
import projektovanje.dto.IDTO;

import java.sql.Connection;
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
    public Boolean azurirajBazu(IDTO dtoProdavacKarata, Connection konekcijaNaBazu) {
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
