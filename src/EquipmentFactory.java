import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 23.04.2017.
 */
public class EquipmentFactory implements Factory {
    @Override
    public SportEquipment createSportEquipment(Category category, String title, int price) {
        return new Equipment(category, title, price);
    }

    @Override
    public Map<SportEquipment, Integer> allGoods() {
        String line = "";
        Equipment equipment;
        Map<SportEquipment, Integer> map = new HashMap<SportEquipment, Integer>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Оборудование.txt");
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                String[] string = line.split(";");
                Category c=Category.valueOf(string[0]);
               equipment= (Equipment) this.createSportEquipment(Category.valueOf(string[0]),string[1], Integer.parseInt(string[2]));
            map.put(equipment, Integer.valueOf(string[3]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
