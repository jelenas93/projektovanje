package projektovanje.bin.klijent;

import projektovanje.bin.nalog.Nalog;

public class Klijent {
    private Nalog korisnickiNalog;
    private String ime;
    private String prezime;
    private String email;

    public Klijent() {
    }

    public Klijent(Nalog korisnickiNalog, String ime, String prezime, String email) {
        this.korisnickiNalog = korisnickiNalog;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
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
