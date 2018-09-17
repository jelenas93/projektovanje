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
                "insert into Plata values(default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        DTOPlata lokalnaVarijabla = (DTOPlata)dtoInstanca;
        preparedStatement.setDouble(1, lokalnaVarijabla.getPlata().getDoprinosZaPenziono());
        preparedStatement.setDouble(2, lokalnaVarijabla.getPlata().getDoprinosZaZdravstveno());
        preparedStatement.setDouble(3, lokalnaVarijabla.getPlata().getDoprinosZaDjecijuZastitu());
        preparedStatement.setDouble(4, lokalnaVarijabla.getPlata().getDoprinosZaZaposljavanje());
        preparedStatement.setDouble(5, lokalnaVarijabla.getPlata().getStopaPoreza());
        preparedStatement.setDouble(6, lokalnaVarijabla.getPlata().getStopaZaPenziono());
        preparedStatement.setDouble(7, lokalnaVarijabla.getPlata().getStopaZaZdravstveno());
        preparedStatement.setDouble(8, lokalnaVarijabla.getPlata().getStopaZaDjecijuZastitu());
        preparedStatement.setDouble(9, lokalnaVarijabla.getPlata().getStopaZaZaposljavanje());
        preparedStatement.setDouble(10, lokalnaVarijabla.getPlata().getNetoTekuciRad());
        preparedStatement.setDouble(11, lokalnaVarijabla.getPlata().getNetoMinuliRad());
        preparedStatement.setDouble(12, lokalnaVarijabla.getPlata().getPorezNaPlatu());
        preparedStatement.setDate(13, new java.sql.Date(lokalnaVarijabla.getPlata().getDatumOd().getTime()));
        preparedStatement.setDate(14,  new java.sql.Date(lokalnaVarijabla.getPlata().getDatumDo().getTime()));
        preparedStatement.setDouble(15, lokalnaVarijabla.getPlata().getBruto());
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
                    resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDouble(3),
                    resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getDouble(6),
                    resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDouble(9),
                    resultSet.getDouble(10), resultSet.getDouble(11), resultSet.getDouble(12),
                    resultSet.getDouble(16), resultSet.getDouble(13),
                    new java.util.Date(resultSet.getDate(14).getTime()),
                    new java.util.Date(resultSet.getDate(15).getTime()));
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
                        "       stopaPoreza = ?," +
                        "       stopaZaPenziono = ?," +
                        "       stopaZaZdravstveno = ?," +
                        "       stopaZaDjecijuZastitu = ?," +
                        "       stopaZaZaposljavanje = ?," +
                        "       netoTekuciRad = ?," +
                        "       netoMinuliRad = ?," +
                        "       porezNaPlatu = ?," +
                        "       datumOd = ?," +
                        "       datumDo = ?," +
                        "       bruto = ?" +
                        "   where idPlate = ?");

        preparedStatement.setDouble(1, lokalnaVarijabla.getPlata().getStopaZaPenziono());
        preparedStatement.setDouble(2,lokalnaVarijabla.getPlata().getDoprinosZaZdravstveno());
        preparedStatement.setDouble(3,lokalnaVarijabla.getPlata().getDoprinosZaDjecijuZastitu());
        preparedStatement.setDouble(4,lokalnaVarijabla.getPlata().getDoprinosZaZaposljavanje());
        preparedStatement.setDouble(5,lokalnaVarijabla.getPlata().getStopaZaPenziono());
        preparedStatement.setDouble(6,lokalnaVarijabla.getPlata().getStopaZaZdravstveno());
        preparedStatement.setDouble(7,lokalnaVarijabla.getPlata().getStopaZaZdravstveno());
        preparedStatement.setDouble(8,lokalnaVarijabla.getPlata().getStopaZaDjecijuZastitu());
        preparedStatement.setDouble(9,lokalnaVarijabla.getPlata().getStopaZaZaposljavanje());
        preparedStatement.setDouble(10,lokalnaVarijabla.getPlata().getNetoTekuciRad());
        preparedStatement.setDouble(11,lokalnaVarijabla.getPlata().getNetoMinuliRad());
        preparedStatement.setDouble(12,lokalnaVarijabla.getPlata().getPorezNaPlatu());
        preparedStatement.setDate(13,new java.sql.Date(lokalnaVarijabla.getPlata().getDatumOd().getTime()));
        preparedStatement.setDate(14,new java.sql.Date(lokalnaVarijabla.getPlata().getDatumDo().getTime()));
        preparedStatement.setDouble(15,lokalnaVarijabla.getPlata().getBruto());
        preparedStatement.setDouble(16,lokalnaVarijabla.getPlata().getIDPlate());
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
                    resultSet.getInt(1), resultSet.getDouble(2), resultSet.getDouble(3),
                    resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getDouble(6),
                    resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDouble(9),
                    resultSet.getDouble(10), resultSet.getDouble(11), resultSet.getDouble(12),
                    resultSet.getDouble(16), resultSet.getDouble(13),
                    new java.util.Date(resultSet.getDate(14).getTime()),
                    new java.util.Date(resultSet.getDate(15).getTime()));
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

    public DTOPlata dobaviZadnjuUmetnutuPlatu(Connection konekcijaNaBazu) throws SQLException {
        DTOPlata dtoPlata = null;
        PreparedStatement ps = konekcijaNaBazu.prepareCall("select last_insert_id() from Plata into @?");
        int lastId = 1;
        ps.setInt(1, lastId);
        ps.executeQuery();

        return dtoPlata;
    }
}
