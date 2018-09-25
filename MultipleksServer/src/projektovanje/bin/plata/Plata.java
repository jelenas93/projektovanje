package projektovanje.bin.plata;

import java.io.Serializable;
import java.util.Date;

    public class Plata implements Serializable {
        public static final long serialVersionUID=9009l;

        public static final double STOPA_ZA_DOPRINOSE=0.33;
        public static final double STOPA_ZA_PIO=0.185;
        public static final double STOPA_ZA_ZDRAVSTVENO=0.12;
        public static final double STOPA_ZA_DJECIJI=0.017;
        public static final double STOPA_ZA_NEZAPOSLENE=0.008;

        private Integer IDPlate;
        private Double doprinosi;
        private Double doprinosZaPenziono;
        private Double doprinosZaZdravstveno;
        private Double doprinosZaDjecijuZastitu;
        private Double doprinosZaZaposljavanje;
        private Date datumOd;
        private Date datumDo;
        private Double bruto;
        private Double isplataRadniku;

        public static final Plata nulaPlata = new Plata();

        public Plata() {
        }

        public Plata(Integer id){
            this.IDPlate = id;
        }

        public Plata(Integer id, Double bruto, Double doprinosi, Double
                doprinosZaPenziono, Double doprinosZaZdravstveno, Double doprinosZaDjecijuZastitu,
                     Double doprinosZaZaposljavanje, Double isplataRadniku, Date datumOd,Date datumDo){
            this.IDPlate=id;
            this.datumOd = datumOd;
            this.datumDo = datumDo;
            this.bruto=bruto;
            this.doprinosi=doprinosi;
            this.doprinosZaPenziono=doprinosZaPenziono;
            this.doprinosZaDjecijuZastitu=doprinosZaDjecijuZastitu;
            this.doprinosZaZaposljavanje=doprinosZaZaposljavanje;
            this.doprinosZaZdravstveno=doprinosZaZdravstveno;
            this.isplataRadniku=isplataRadniku;

        }

        public Plata(Integer id, Double brutoOsnovna, Date datumOd, Date datumDo){
            this.IDPlate = id;
            this.datumOd = datumOd;
            this.datumDo = datumDo;
            this.bruto=brutoOsnovna;
            this.doprinosi=bruto*STOPA_ZA_DOPRINOSE;
            this.doprinosZaPenziono=bruto*STOPA_ZA_PIO;
            this.doprinosZaDjecijuZastitu=bruto*STOPA_ZA_DJECIJI;
            this.doprinosZaZaposljavanje=bruto*STOPA_ZA_NEZAPOSLENE;
            this.doprinosZaZdravstveno=bruto*STOPA_ZA_ZDRAVSTVENO;
            this.isplataRadniku=bruto-doprinosi;
        }

        public Double getDoprinosi() {
            return doprinosi;
        }

        public void setDoprinosi(Double doprinosi) {
            this.doprinosi = doprinosi;
        }

        public Double getIsplataRadniku() {
            return isplataRadniku;
        }

        public void setIsplataRadniku(Double isplataRadniku) {
            this.isplataRadniku = isplataRadniku;
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



        public Double getBruto() {
            return bruto;
        }

        public void setBruto(Double bruto) {
            this.bruto = bruto;
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
                    ",\n datumOd=" + datumOd +
                    ",\n datumDo=" + datumDo +
                    ",\n bruto=" + bruto +
                    '}';
        }
    }



