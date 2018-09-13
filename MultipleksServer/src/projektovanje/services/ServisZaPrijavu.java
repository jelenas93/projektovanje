package projektovanje.services;

import projektovanje.bin.nalog.Nalog;
import projektovanje.exceptions.PogresniKredencijaliException;

import java.util.List;

public class ServisZaPrijavu {

    private static boolean provjeriKredencijale(String korisnickoIme,String lozinka){
        return false;
    }

    public static boolean login(String korisnickoIme,String lozinka)throws PogresniKredencijaliException{
        if(provjeriKredencijale(korisnickoIme,lozinka)){
            return true;
        }
        else throw new PogresniKredencijaliException();
    }


}
