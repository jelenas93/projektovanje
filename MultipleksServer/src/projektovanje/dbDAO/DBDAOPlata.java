package projektovanje.dbDAO;

import projektovanje.bin.plata.Plata;
import projektovanje.dto.DTOPlata;
import projektovanje.dto.DTOPonuda;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOPlata implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("" +
                "insert into Plata values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        DTOPlata lokalnaVarijabla = (DTOPlata)dtoInstanca;
        preparedStatement.setDouble(1, lokalnaVarijabla.getPlata().getDoprinosZaPenziono());
        preparedStatement.setDouble(2, lokalnaVarijabla.getPlata().getDoprinosZaZdravstveno());
        preparedStatement.setDouble(3, lokalnaVarijabla.getPlata().getDoprinosZaDjecijuZastitu());
        preparedStatement.setDouble(4, lokalnaVarijabla.getPlata().getDoprinosZaZaposljavanje());
        preparedStatement.setDate(5, new java.sql.Date(lokalnaVarijabla.getPlata().getDatumOd().getTime()));
        preparedStatement.setDate(6,  new java.sql.Date(lokalnaVarijabla.getPlata().getDatumDo().getTime()));
        preparedStatement.setDouble(7, lokalnaVarijabla.getPlata().getBruto());
        preparedStatement.setDouble(8, lokalnaVarijabla.getPlata().getDoprinosi());
        preparedStatement.setDouble(9, lokalnaVarijabla.getPlata().getIsplataRadniku());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public List<DTOPlata> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTOPlata> listaPlata = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Plata");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Plata plata = new Plata(
                    resultSet.getInt("idSale"), resultSet.getDouble("bruto"),
                    resultSet.getDouble("doprinosi"), resultSet.getDouble("doprinosZaPenziono"), resultSet.getDouble("doprinosZaZdravstveno"),
                    resultSet.getDouble("doprinosZaDjecijuZastitu"), resultSet.getDouble("doprinosZaZaposljavanje"), resultSet.getDouble("isplataRadniku"),
                   new java.util.Date(resultSet.getDate("datumOd").getTime()),
                    new java.util.Date(resultSet.getDate("datumDo").getTime()));
            listaPlata.add(new DTOPlata(plata));
        }

        return listaPlata;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DTOPlata lokalnaVarijabla = (DTOPlata) list;

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement(
                "update Plata" +
                        "   set  doprinosZaPenziono= ?," +
                        "       doprinosZaZdravstveno = ?," +
                        "       doprinosZaDjecijuZastitu = ?," +
                        "       doprinosZaZaposljavanje = ?," +
                        "       datumOd = ?," +
                        "       datumDo = ?," +
                        "       bruto = ?," +
                        "       doprinosi = ?," +
                        "       isplataRadniku = ?" +
                        "   where idPlate = ?");

        preparedStatement.setDouble(1,lokalnaVarijabla.getPlata().getDoprinosZaPenziono());
        preparedStatement.setDouble(2,lokalnaVarijabla.getPlata().getDoprinosZaZdravstveno());
        preparedStatement.setDouble(3,lokalnaVarijabla.getPlata().getDoprinosZaDjecijuZastitu());
        preparedStatement.setDouble(4,lokalnaVarijabla.getPlata().getDoprinosZaZaposljavanje());
        preparedStatement.setDate(5,new java.sql.Date(lokalnaVarijabla.getPlata().getDatumOd().getTime()));
        preparedStatement.setDate(6,new java.sql.Date(lokalnaVarijabla.getPlata().getDatumDo().getTime()));
        preparedStatement.setDouble(7,lokalnaVarijabla.getPlata().getBruto());
        preparedStatement.setDouble(8,lokalnaVarijabla.getPlata().getDoprinosi());
        preparedStatement.setInt(9,lokalnaVarijabla.getPlata().getIDPlate());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOPlata localPlata;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Plata where idPlate = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            Plata plata = new Plata(
                    resultSet.getInt("idSale"), resultSet.getDouble("bruto"),
                    resultSet.getDouble("doprinosi"), resultSet.getDouble("doprinosZaPenziono"), resultSet.getDouble("doprinosZaZdravstveno"),
                    resultSet.getDouble("doprinosZaDjecijuZastitu"), resultSet.getDouble("doprinosZaZaposljavanje"), resultSet.getDouble("isplataRadniku"),
                    new java.util.Date(resultSet.getDate("datumOd").getTime()),
                    new java.util.Date(resultSet.getDate("datumDo").getTime()));
            localPlata = new DTOPlata(plata);
        }else{
            localPlata = null;
        }

        return localPlata;
    }

    @Override
    public List<DTOPlata> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }

}
