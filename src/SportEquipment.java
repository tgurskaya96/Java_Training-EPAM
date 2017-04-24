/**
 * Created by user on 23.04.2017.
 */
public abstract class SportEquipment {
    private Category category;
    private String title;
    private int price;


    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String informationAboutGood() {
        String s = "Название товара - " + getTitle() + " Категория - " + getCategory() + " Цена - " + getPrice();
        return s;
    }

    SportEquipment(Category category, String title, int price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        SportEquipment e = (SportEquipment) o;
        return ((this.informationAboutGood().equals(e.informationAboutGood())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (title == null ? 0 : title.hashCode());
        result = 37 * result + (category.toString() == null ? 0 : category.toString().hashCode());
        result = 37 * result + price;
        return result;
    }

    public String infForFile(){
        return category.toString()+";"+title+";"+price;
    }
}
