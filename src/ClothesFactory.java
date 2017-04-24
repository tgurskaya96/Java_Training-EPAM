import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 23.04.2017.
 */
public class ClothesFactory implements Factory {

    @Override
    public SportEquipment createSportEquipment(Category category,String title,int price) {
        return new Clothes(category,title,price,0);
    }


    @Override
    public Map<SportEquipment, Integer> allGoods() {
        String line = "";
        Clothes clothes;
        Map<SportEquipment, Integer> map = new HashMap<SportEquipment, Integer>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Одежда.txt");
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                String[] string = line.split(";");
                clothes= (Clothes) this.createSportEquipment(Category.valueOf(string[0]),string[1], Integer.parseInt(string[2]));
                clothes.setSize(Integer.valueOf(string[3]));
                map.put(clothes, Integer.valueOf(string[4]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
