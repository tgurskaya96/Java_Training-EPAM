import java.io.*;

/**
 * Created by user on 23.04.2017.
 */
public class WorkWithFile {
    public void addToFile(String filename, String text) throws FileNotFoundException {

        File file = new File(filename);
        if (file.exists()) {
            PrintStream printStream = new PrintStream(new FileOutputStream(file.getAbsoluteFile(), true), true);
            printStream.println(text);
            printStream.close();
        } else System.out.println("File not found");
    }

    public void cleanFile(String path){

        try {
            FileWriter fstream1 = new FileWriter(path);// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(""); // очищаем, перезаписав поверх пустую строку
            out1.close(); // закрываем
        } catch (Exception e)
        {System.err.println("Error in file cleaning: " + e.getMessage());}
    }

}
