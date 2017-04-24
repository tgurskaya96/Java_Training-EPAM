/**
 * Created by user on 23.04.2017.
 */
public class Clothes extends SportEquipment {
    private int size;
    Clothes(Category category,String title,int price,int size){
        super(category,title,price);
        this.size=size;
    }

    @Override
    public int hashCode() {
        int b=super.hashCode()+37*size;
        return b;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        boolean b=super.equals(o)&&(((Clothes) o).getSize()==this.getSize());
        return b;
    }

    @Override
    public String informationAboutGood() {
        return super.informationAboutGood()+ " размер - "+size;
    }

    @Override
    public String infForFile() {
        return super.infForFile()+";"+size;
    }
}
