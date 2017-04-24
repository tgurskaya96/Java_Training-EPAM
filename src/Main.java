import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop();
        boolean error = false;
        String[] str;
        int quant = 0;
        String enterString = "Нажмите \n" +
                "1  - для просмотра товаров\n2 - для того, чтобы взять товар в аренду \n3 - чтобы выйти";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Добро пожаловать в магазин спортивных товаров");
            while (true) {
                System.out.println(enterString);
                do {
                    String s = br.readLine();
                    if (!s.equals("3")) {
                        if (s.equals("1")) {
                            System.out.println("1-просмотр взятых в аренду товаров\n2 - просмотр имеющихся товаров");
                            do {
                                s = br.readLine();
                                if (s.equals("1")) {
                                    error = false;
                                    System.out.println("Отчет о взятых в аренду");
                                    shop.printRented();
                                } else if (s.equals("2")) {
                                    error = false;
                                    System.out.println("Имеющиеся товары");
                                    shop.printGoods();
                                } else {
                                    error = true;
                                    System.out.println("Неверный номер, введите 1 или 2");
                                }
                            } while (error);
                        } else if (s.equals("2")) {

                            System.out.println("Введите своё имя");
                            s = br.readLine();
                            System.out.println("Cколько товаров планируете арендовать? Доступна аренда не более 3 товаров");
                            int quantity = 0;
                            do {
                                error = false;
                                quantity = Integer.parseInt(br.readLine());
                                if (quantity > 3) {
                                    error = true;
                                    System.out.println("Неверное количество. Введите верное");
                                }
                            } while (error);
                            RentUnit rentUnit = new RentUnit(s, quantity);
                            for (int i = 0; i < quantity; ) {
                                SportEquipment sportEquipment;
                                Factory f;
                                String st = Arrays.toString(Category.values());
                                System.out.println("Введите вид спорта. Доступные виды спорта:\n" + st);
                                Category category = Category.Хоккей;
                                do {
                                    try {
                                        category = Category.valueOf(br.readLine());
                                        error = false;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Неверный вид спорта. Введите верный");
                                        error = true;
                                    }
                                } while (error);
                                System.out.println("1-одежда\n2 - оборудование");
                                do {
                                    s = br.readLine();
                                    if (s.equals("1")) {
                                        System.out.println("Введите через точку с запятой название товара, цену, размер и количество");
                                        do {
                                            error = false;
                                            str = br.readLine().split(";");
                                            quant = Integer.parseInt(str[3]);
                                            if (quant > quantity - i) {
                                                error = true;
                                                System.out.println("Неверное количество, введите верные параметры. Количество не больше " + (quantity - i));
                                            } else {
                                                error = false;
                                                for (int j = 0; j < quant; j++) {
                                                    f = new ClothesFactory();
                                                    sportEquipment = f.createSportEquipment(category, str[0], Integer.parseInt(str[1]));
                                                    Clothes c = (Clothes) sportEquipment;
                                                    c.setSize(Integer.parseInt(str[2]));
                                                    try {
                                                        shop.rentUnit(rentUnit, sportEquipment, i);
                                                        i++;
                                                        error = false;
                                                    } catch (NullPointerException e) {
                                                        error = true;
                                                        System.out.println("Товар не найден, введите верный товар");
                                                        j=quant;
                                                    }
                                                }
                                            }
                                        } while (error);
                                        System.out.println("Оформлено! ");
                                        if (i < 2) System.out.println("Следующий товар:");
                                    } else if (s.equals("2")) {
                                        System.out.println("Введите через точку с запятой название товара,цену и количество");
                                        do {
                                            error = false;
                                            str = br.readLine().split(";");
                                            quant = Integer.parseInt(str[2]);
                                            if (quant > quantity - i) {
                                                error = true;
                                                System.out.println("Неверное количество, введите верные параметры. Количество не больше " + (quantity - i));
                                            } else {
                                                error = false;
                                                for (int j = 0; j < Integer.parseInt(str[2]); j++) {
                                                    f = new EquipmentFactory();
                                                    try {
                                                        sportEquipment = f.createSportEquipment(category, str[0], Integer.parseInt(str[1]));
                                                        shop.rentUnit(rentUnit, sportEquipment, i);
                                                        error = false;
                                                        i++;
                                                    } catch (NullPointerException e) {
                                                        error = true;
                                                        System.out.println("Товар не найден, введите верный товар");
                                                        j=quant;
                                                    }
                                                }
                                            }
                                        } while (error);
                                        System.out.println("Оформлено! ");
                                        if (i < 2) System.out.println("Следующий товар:");
                                    } else {
                                        error = true;
                                        System.out.println("Неверный номер, введите 1 или 2");
                                    }
                                } while (error);
                            }
                            shop.rewrire();
                            rentUnit.write();
                        }
                    } else return;
                } while (error);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
