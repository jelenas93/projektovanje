package projektovanje.ostalo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyFileUtils {

private static String configFilePath = "property.txt";

public static String getValue(String property){
    try {
        return Files.readAllLines(Paths.get(configFilePath)).stream().parallel().filter(x->x.split("#")[0].equals(property)).map(x->x.split("#")[1]).findFirst().get();
    } catch (IOException ex) {
        Logger.getLogger(PropertyFileUtils.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (NoSuchElementException ex){
        return null;
    }
}

public static boolean addNewProperty(String property,String value){
    try {
        Files.newBufferedWriter(Paths.get(configFilePath)).append(property+"#"+value);
        return true;
    } catch (IOException ex) {
        return false;
    }
}


}
