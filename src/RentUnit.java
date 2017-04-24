import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by user on 23.04.2017.
 */
public class RentUnit extends WorkWithFile {
    private static File f;
    private SportEquipment[] units;
    private String nameOfUser;

    RentUnit(String nameOfUser, int n) {
        this.nameOfUser = nameOfUser;
        units = new SportEquipment[n];
    }

    public void addUnit(SportEquipment sportEquipment, int num) {
        units[num] = sportEquipment;
    }

    public void write() {

        for (int i = 0; i < units.length; i++) {
            try {
                String s = nameOfUser + ";" + units[i].infForFile();
                addToFile("Арендованные товары.txt", s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
