package projektovanje.exceptions;

public class PogresniKredencijaliException extends Exception {

   public  PogresniKredencijaliException(){
        this("Pogresni kredencijali");
    }

    public PogresniKredencijaliException(String poruka){
        super(poruka);
    }
}
