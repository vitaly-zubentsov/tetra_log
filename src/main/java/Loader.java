import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

     public String downloadLogFromFile(String pathToFile) throws FileNotFoundException{

        try ( Scanner scannerForFileWithLogs = new Scanner(new File(pathToFile))) {
            StringBuilder stringBuilder = new StringBuilder();

            while (scannerForFileWithLogs.hasNextLine()) {
                stringBuilder.append(scannerForFileWithLogs.nextLine()).append("\n");
                         }
            return stringBuilder.toString();
        }
     }
}
