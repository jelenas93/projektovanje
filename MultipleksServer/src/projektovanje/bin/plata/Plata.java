package projektovanje.bin.plata;

import java.io.Serializable;
import java.util.Date;

public class Plata implements Serializable {

    private Integer IDPlate;
    private Double doprinosZaPenziono;
    private Double doprinosZaZdravstveno;
    private Double doprinosZaDjecijuZastitu;
    private Double doprinosZaZaposljavanje;
    private Double stopaPoreza;
    private Double stopaZaPenziono;
    private Double stopaZaZdravstveno;
    private Double stopaZaDjecijuZastitu;
    private Double stopaZaZaposljavanje;
    private Double netoTekuciRad;
    private Double netoMinuliRad;
    private Double porezNaPlatu;
    private Date datumOd;
    private Date datumDo;
    private Double bruto;

    public static final Plata nulaPlata = new Plata(1,0.0,0.0,
                                0.0,0.0,0.0,
                                    0.0,0.0,0.0,
                                0.0,0.0,0.0,0.0,
                                    0.0,new Date(),new Date());
    public Plata() {
    }

    public Plata(Integer id){
        this.IDPlate = id;
    }

    public Plata(Integer id, Double doprinosZaPenziono, Double doprinosZaZdravstveno, Double doprinosZaDjecijuZastitu, Double doprinosZaZaposljavanje, Double stopaPoreza, Double stopaZaPenziono, Double stopaZaZdravstveno, Double stopaZaDjecijuZastitu, Double stopaZaZaposljavanje, Double netoTekuciRad, Double netoMinuliRad, Double bruto, Double porezNaPlatu, Date datumOd, Date datumDo) {
        this.IDPlate = id;
        this.doprinosZaPenziono = doprinosZaPenziono;
        this.doprinosZaZdravstveno = doprinosZaZdravstveno;
        this.doprinosZaDjecijuZastitu = doprinosZaDjecijuZastitu;
        this.doprinosZaZaposljavanje = doprinosZaZaposljavanje;
        this.stopaPoreza = stopaPoreza;
        this.stopaZaPenziono = stopaZaPenziono;
        this.stopaZaZdravstveno = stopaZaZdravstveno;
        this.stopaZaDjecijuZastitu = stopaZaDjecijuZastitu;
        this.stopaZaZaposljavanje = stopaZaZaposljavanje;
        this.netoTekuciRad = netoTekuciRad;
        this.netoMinuliRad = netoMinuliRad;
        this.bruto = bruto;
        this.porezNaPlatu = porezNaPlatu;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Double getDoprinosZaPenziono() {
        return doprinosZaPenziono;
    }

    public void setDoprinosZaPenziono(Double doprinosZaPenziono) {
        this.doprinosZaPenziono = doprinosZaPenziono;
    }

    public Double getDoprinosZaZdravstveno() {
        return doprinosZaZdravstveno;
    }

    public void setDoprinosZaZdravstveno(Double doprinosZaZdravstveno) {
        this.doprinosZaZdravstveno = doprinosZaZdravstveno;
    }

    public Double getDoprinosZaDjecijuZastitu() {
        return doprinosZaDjecijuZastitu;
    }

    public void setDoprinosZaDjecijuZastitu(Double doprinosZaDjecijuZastitu) {
        this.doprinosZaDjecijuZastitu = doprinosZaDjecijuZastitu;
    }

    public Double getDoprinosZaZaposljavanje() {
        return doprinosZaZaposljavanje;
    }

    public void setDoprinosZaZaposljavanje(Double doprinosZaZaposljavanje) {
        this.doprinosZaZaposljavanje = doprinosZaZaposljavanje;
    }

    public Double getStopaPoreza() {
        return stopaPoreza;
    }

    public void setStopaPoreza(Double stopaPoreza) {
        this.stopaPoreza = stopaPoreza;
    }

    public Double getStopaZaPenziono() {
        return stopaZaPenziono;
    }

    public void setStopaZaPenziono(Double stopaZaPenziono) {
        this.stopaZaPenziono = stopaZaPenziono;
    }

    public Double getStopaZaZdravstveno() {
        return stopaZaZdravstveno;
    }

    public void setStopaZaZdravstveno(Double stopaZaZdravstveno) {
        this.stopaZaZdravstveno = stopaZaZdravstveno;
    }

    public Double getStopaZaDjecijuZastitu() {
        return stopaZaDjecijuZastitu;
    }

    public void setStopaZaDjecijuZastitu(Double stopaZaDjecijuZastitu) {
        this.stopaZaDjecijuZastitu = stopaZaDjecijuZastitu;
    }

    public Double getStopaZaZaposljavanje() {
        return stopaZaZaposljavanje;
    }

    public void setStopaZaZaposljavanje(Double stopaZaZaposljavanje) {
        this.stopaZaZaposljavanje = stopaZaZaposljavanje;
    }

    public Double getNetoTekuciRad() {
        return netoTekuciRad;
    }

    public void setNetoTekuciRad(Double netoTekuciRad) {
        this.netoTekuciRad = netoTekuciRad;
    }

    public Double getNetoMinuliRad() {
        return netoMinuliRad;
    }

    public void setNetoMinuliRad(Double netoMinuliRad) {
        this.netoMinuliRad = netoMinuliRad;
    }

    public Double getBruto() {
        return bruto;
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public Double getPorezNaPlatu() {
        return porezNaPlatu;
    }

    public void setPorezNaPlatu(Double porezNaPlatu) {
        this.porezNaPlatu = porezNaPlatu;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Integer getIDPlate() {
        return IDPlate;
    }

    public void setIDPlate(Integer IDPlate) {
        this.IDPlate = IDPlate;
    }

    @Override
    public String toString() {
        return "Plata{" +
                "IDPlate=" + IDPlate +
                ",\n doprinosZaPenziono=" + doprinosZaPenziono +
                ",\n doprinosZaZdravstveno=" + doprinosZaZdravstveno +
                ",\n doprinosZaDjecijuZastitu=" + doprinosZaDjecijuZastitu +
                ",\n doprinosZaZaposljavanje=" + doprinosZaZaposljavanje +
                ",\n stopaPoreza=" + stopaPoreza +
                ",\n stopaZaPenziono=" + stopaZaPenziono +
                ",\n stopaZaZdravstveno=" + stopaZaZdravstveno +
                ",\n stopaZaDjecijuZastitu=" + stopaZaDjecijuZastitu +
                ",\n stopaZaZaposljavanje=" + stopaZaZaposljavanje +
                ",\n netoTekuciRad=" + netoTekuciRad +
                ",\n netoMinuliRad=" + netoMinuliRad +
                ",\n porezNaPlatu=" + porezNaPlatu +
                ",\n datumOd=" + datumOd +
                ",\n datumDo=" + datumDo +
                ",\n bruto=" + bruto +
                '}';
    }
}
