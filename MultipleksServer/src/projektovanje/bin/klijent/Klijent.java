package projektovanje.bin.klijent;

import projektovanje.bin.nalog.Nalog;

import java.io.Serializable;

public class Klijent implements Serializable{
    public static final long serialVersionUID=9006l;
	private Integer idKlijenta;
    private Nalog korisnickiNalog;
    private String ime;
    private String prezime;
    private String email;

    public Klijent() {
    }

    @Override
    public String toString() {
        return "Klijent{" +
                "idKlijenta=" + idKlijenta +
                ", korisnickiNalog=" + korisnickiNalog.getKorisnickiNalog() +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Klijent(Integer idKlijenta, Nalog korisnickiNalog, String ime, String prezime, String email) {
        this.idKlijenta = idKlijenta;
		this.korisnickiNalog = korisnickiNalog;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }
	
	public Integer getIdKlijenta(){
		return this.idKlijenta;
	}
	
	public void setIdKlijenta(Integer idKlijenta){
		this.idKlijenta = idKlijenta;
	}

    public Nalog getKorisnickiNalog() {
        return korisnickiNalog;
    }

    public void setKorisnickiNalog(Nalog korisnickiNalog) {
        this.korisnickiNalog = korisnickiNalog;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
