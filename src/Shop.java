import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 23.04.2017.
 */
public class Shop extends WorkWithFile {

    private Map<SportEquipment, Integer> goods;
    // private ArrayList<RentUnit> rentUnits;

    Shop() {
        goods = new HashMap<SportEquipment, Integer>();
        create();
    }

    public void printGoods() {
        for (Map.Entry<SportEquipment, Integer> e : goods.entrySet()) {
            System.out.println(e.getKey().informationAboutGood() + " количество на складе - " + e.getValue());

        }
    }

    public Integer findGood(SportEquipment s) {
        Integer b = goods.get(s);
        return b;
    }

    public void rentUnit(RentUnit rentUnit, SportEquipment s, int num) {
        int quantity = findGood(s);
        if (quantity > 0) {
            if (quantity > 1)
                goods.put(s, quantity - 1);
            else goods.remove(s);
            rentUnit.addUnit(s, num);
        }
    }

    public void create() {
        Factory f1 = new ClothesFactory();
        goods.putAll(f1.allGoods());
        f1 = new EquipmentFactory();
        goods.putAll(f1.allGoods());
    }

    public void rewrire() {
        cleanFile("Одежда.txt");
        cleanFile("Оборудование.txt");
        for (Map.Entry<SportEquipment, Integer> e : goods.entrySet()) {
            try {
                String s = e.getKey().infForFile() + ";" + e.getValue();
                if (e.getKey() instanceof Clothes)
                    addToFile("Одежда.txt", s);
                else addToFile("Оборудование.txt", s);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
    }

    public void printRented() {
        String line="";
        try {
            FileReader fileReader = new FileReader("Арендованные товары.txt");
            BufferedReader br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                String[] string = line.split(";");
                String s=string[0]+" взял(а)в аренду "+string[2]+" из категории "+string[1]+" по стоимости "+string[3];
                if(string.length>4)s+=" размер "+string[4];
                System.out.println(s);
            }
        }catch (Exception e){
        e.printStackTrace();}
    }

    }
