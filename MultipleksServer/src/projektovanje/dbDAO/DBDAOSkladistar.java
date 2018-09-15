package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Skladistar;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOSkladistar;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOSkladistar implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement upisSkladistara = konekcijaNaBazu.prepareStatement("insert into Skladistar values (?)");
        DTOSkladistar lokalniSkladistar = (DTOSkladistar) dtoInstanca;
        upisSkladistara.setInt(1, lokalniSkladistar.getSkladistar().getIdSkladistara());
        upisSkladistara.executeUpdate();
        return true;}

    @Override
    public List<DTOSkladistar> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTOSkladistar> listaSkladistara = new ArrayList<DTOSkladistar>();
        PreparedStatement citanjeSvihSkladistara = konekcijaNaBazu.prepareStatement("select * from Skladistar");
        ResultSet resultSet = citanjeSvihSkladistara.executeQuery();
        while(resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            Skladistar Skladistar = new Skladistar(zaposleniDTO.getZaposleni());
            DTOSkladistar dtoAdministrator = new DTOSkladistar(Skladistar);
            listaSkladistara.add(dtoAdministrator);
        }
        return listaSkladistara;}

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOSkladistar SkladistarDTO = (DTOSkladistar)list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)SkladistarDTO.getSkladistar());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOSkladistar lokalniSkladistar;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Skladistar where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Skladistar var = new Skladistar();
        if (resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            var = new Skladistar(zaposleniDTO.getZaposleni());
            lokalniSkladistar = new DTOSkladistar(var);
        }else{
            lokalniSkladistar = null;
        }
        return lokalniSkladistar;
    }

    @Override
    public List<DTOSkladistar> ispisi(Connection konekcijaNaBazu) throws SQLException {

        return citajIzBaze(konekcijaNaBazu);
    }
}
