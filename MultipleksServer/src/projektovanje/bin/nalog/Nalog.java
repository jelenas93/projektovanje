package projektovanje.bin.nalog;

import java.io.Serializable;

public class Nalog implements Serializable {
    private String korisnickiNalog;
    private String lozinkaHash;

    public Nalog() {
    }

    public Nalog(String korisnickiNalog, String lozinkaHash) {
        this.korisnickiNalog = korisnickiNalog;
        this.lozinkaHash = lozinkaHash;
    }

    public Nalog(String korisnickoIme){
        korisnickiNalog = korisnickoIme;
    }
    public String getKorisnickiNalog() {
        return korisnickiNalog;
    }

    public void setKorisnickiNalog(String korisnickiNalog) {
        this.korisnickiNalog = korisnickiNalog;
    }

    public String getLozinkaHash() {
        return lozinkaHash;
    }

    public void setLozinkaHash(String lozinkaHash) {
        this.lozinkaHash = lozinkaHash;
    }

    @Override
    public String toString() {
        return "Nalog{" +
                "korisnickiNalog='" + korisnickiNalog + '\'' +
                ", lozinkaHash='" + lozinkaHash + '\'' +
                '}';
    }
}
