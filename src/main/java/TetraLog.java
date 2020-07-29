
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TetraLog {

    public static void main(String[] args) {

        // FileReader fileWithLogs = new FileReader(new File("/main/resources/commutation.txt"));
        String stringWithLogs = "";
        try (Scanner scannerForFileWithLogs = new Scanner(new File("src/main/resources/commutation.txt"))) {
            StringBuilder stringBuilder = new StringBuilder();

            while (scannerForFileWithLogs.hasNextLine()) {
                stringBuilder.append(scannerForFileWithLogs.nextLine()).append("\n");
            }
            stringWithLogs = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(stringWithLogs);

        //TODO разбить логи по регулярке  ^(0\\d{1}.\\d{7}, )
    }
}
