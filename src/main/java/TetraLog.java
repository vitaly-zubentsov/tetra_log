
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TetraLog {

    public static void main(String[] args) {

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

        //Разбиваем строку в соотвествии с регулярным выражением для поиска идентификатора сообщения
        //Сам идентификатор отбрасывается, так как для приложения не будет использваться
        //при разбиении образуется совпадение нулевой длины в начале строки
        String[] arrayOfCommutationMessages = stringWithLogs.split("0\\d\\.\\d{5},", -1);

        //Используем фильтр и выводим сообщения коммутации
        Arrays.stream(arrayOfCommutationMessages).parallel().filter(Pattern.compile("E1_CONNECTION-").asPredicate()).forEach(System.out::println);

    }
}
