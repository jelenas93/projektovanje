package projektovanje.dbDAO;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOArtikal;
import projektovanje.dto.IDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOArtikal implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoArtikal, Connection konekcijaNaBazu) throws java.sql.SQLException{
        Boolean uspjesno = false;
        DTOArtikal artikal = (DTOArtikal)dtoArtikal;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Artikal values(default, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, artikal.getArtikal().getNaziv());
        preparedStatement.setInt(2, artikal.getArtikal().getKolicinaNaStanju());
        preparedStatement.setDouble(3, artikal.getArtikal().getJedinicnaCijena());
        preparedStatement.setString(4, artikal.getArtikal().getTip());
        preparedStatement.setString(5, artikal.getArtikal().getBarKod());
        preparedStatement.setInt(6, artikal.getArtikal().getIdZaposlenog());
        preparedStatement.executeUpdate();
        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOArtikal> citajIzBaze(Connection konekcijaNaBazu) throws SQLException{
        List<DTOArtikal> listaArtikala = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Artikal");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setIdZaposlenog(resultSet.getInt(7));
            Artikal artikal = new Artikal(resultSet.getInt(1), resultSet.getString(2)
                    , resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5)
                    , resultSet.getString(6)
                    , zaposleni);
            DTOArtikal dtoArtikal = new DTOArtikal(artikal);
            listaArtikala.add(dtoArtikal);
        }

        return listaArtikala;
    }

    @Override
    public Boolean azurirajBazu(IDTO dtoArtikal, Connection konekcijaNaBazu) throws SQLException {
        Boolean uspjesno = false;

        DTOArtikal artikal = (DTOArtikal) dtoArtikal;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement(
                "update Artikal" +
                        "   set naziv = ?," +
                        "   kolicinaNaStanju = ?," +
                        "   jedinicnaCijena = ?," +
                        "   tip = ?," +
                        "   barKod = ?," +
                        "   idZaposlenog = ?" +
                        "   where idArtikla = ?");

        preparedStatement.setString(1, artikal.getArtikal().getNaziv());
        preparedStatement.setInt(2, artikal.getArtikal().getKolicinaNaStanju());
        preparedStatement.setDouble(3, artikal.getArtikal().getJedinicnaCijena());
        preparedStatement.setString(4, artikal.getArtikal().getTip());
        preparedStatement.setString(5, artikal.getArtikal().getBarKod());
        preparedStatement.setInt(6, artikal.getArtikal().getIdZaposlenog());
        preparedStatement.setInt(7, artikal.getArtikal().getIdArtikla());


        preparedStatement.executeUpdate();
        uspjesno = true;
        return uspjesno;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws java.sql.SQLException{
        DTOArtikal lokalniArtikal;

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Artikal where idArtikla = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Artikal artikal = new Artikal();
        if (resultSet.next()){
            artikal.setIdArtikla(resultSet.getInt(1));
            artikal.setNaziv(resultSet.getString(2));
            artikal.setKolicinaNaStanju(resultSet.getInt(3));
            artikal.setJedinicnaCijena(resultSet.getDouble(4));
            artikal.setTip(resultSet.getString(5));
            artikal.setBarKod(resultSet.getString(6));
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setIdZaposlenog(resultSet.getInt(7));
            artikal.setZaposleni(zaposleni);
            lokalniArtikal = new DTOArtikal(artikal);
        }else{
            lokalniArtikal = null;
        }
        return lokalniArtikal;
    }

    @Override
    public List<DTOArtikal> ispisi(Connection konekcija) throws java.sql.SQLException{
        return citajIzBaze(konekcija);
    }
}
