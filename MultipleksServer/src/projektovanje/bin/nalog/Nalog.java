package projektovanje.bin.nalog;

public class Nalog {
    private String korisnickiNalog;
    private String lozinkaHash;

    public Nalog() {
    }

    public Nalog(String korisnickiNalog, String lozinkaHash) {
        this.korisnickiNalog = korisnickiNalog;
        this.lozinkaHash = lozinkaHash;
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
}
