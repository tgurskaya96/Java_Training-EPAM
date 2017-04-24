import java.util.Map;

/**
 * Created by user on 23.04.2017.
 */
public interface Factory {
    public SportEquipment createSportEquipment(Category category,String title,int price);
    public Map<SportEquipment,Integer> allGoods();
}
