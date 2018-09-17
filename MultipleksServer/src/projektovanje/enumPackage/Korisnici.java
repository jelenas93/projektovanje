package projektovanje.enumPackage;

public enum Korisnici {
    ADMINISTRATOR(0),
    KINOOPERATER(1),
    MENADZER(2),
    PRODAVACHARANEIPICA(3),
    PRODAVACKARATA(4),
    RACUNOVODJA(5),
    SKLADISTAR(6),
    KLIJENT(7);


    private final int value;
    private Korisnici(int value){
        this.value = value;
    }

    public Integer getSkladistar(){
        Korisnici korisnik = Korisnici.SKLADISTAR;
        return this.value;
    }

    public Integer getKlijent(){
        Korisnici korisnik = Korisnici.KLIJENT;
        return this.value;
    }

    public Integer getAdministrator(){
        Korisnici korisnik = Korisnici.ADMINISTRATOR;
        return this.value;
    }

    public Integer getKinoopreater(){
        Korisnici korisnik = Korisnici.KINOOPERATER;
        return this.value;
    }

    public Integer getMenadzer(){
        Korisnici korisnik = Korisnici.MENADZER;
        return this.value;
    }

    public Integer getProdavacHraneIPica(){
        Korisnici korisnik = Korisnici.PRODAVACHARANEIPICA;
        return this.value;
    }

    public Integer getProdavacKarata(){
        Korisnici korisnik = Korisnici.PRODAVACKARATA;
        return this.value;
    }

    public Integer getRacunovodja(){
        Korisnici korisnik = Korisnici.RACUNOVODJA;
        return this.value;
    }
}
