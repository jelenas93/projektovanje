package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.ProdavacKarata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOProdavacKarata;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOProdavacKarata implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement upisProdavacKarataa = konekcijaNaBazu.prepareStatement("insert into ProdavacKarata values (?)");
        DTOProdavacKarata lokalniProdavacKarata = (DTOProdavacKarata) dtoInstanca;
        upisProdavacKarataa.setInt(1, lokalniProdavacKarata.getProdavacKarata().getIdProdavcaKarata());
        upisProdavacKarataa.executeUpdate();
        return true;}

    @Override
    public List<DTOProdavacKarata> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTOProdavacKarata> listaProdavacKarataa = new ArrayList<DTOProdavacKarata>();
        PreparedStatement citanjeSvihProdavacKarataa = konekcijaNaBazu.prepareStatement("select * from ProdavacKarata");
        ResultSet resultSet = citanjeSvihProdavacKarataa.executeQuery();
        while(resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            ProdavacKarata ProdavacKarata = new ProdavacKarata(zaposleniDTO.getZaposleni());
            DTOProdavacKarata dtoAdministrator = new DTOProdavacKarata(ProdavacKarata);
            listaProdavacKarataa.add(dtoAdministrator);
        }
        return listaProdavacKarataa;}

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOProdavacKarata ProdavacKarataDTO = (DTOProdavacKarata)list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)ProdavacKarataDTO.getProdavacKarata());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOProdavacKarata lokalniProdavacKarata;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from ProdavacKarata where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        ProdavacKarata var = new ProdavacKarata();
        if (resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            var = new ProdavacKarata(zaposleniDTO.getZaposleni());
            lokalniProdavacKarata = new DTOProdavacKarata(var);
        }else{
            lokalniProdavacKarata = null;
        }
        return lokalniProdavacKarata;
    }

    @Override
    public List<DTOProdavacKarata> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
