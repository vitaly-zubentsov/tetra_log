
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;

public class TetraLog {

    public static void main(String[] args) throws FileNotFoundException {

        Loader loader = new Loader();
        String stringWithLogs = loader.downloadLogFromFile("src/main/resources/commutation.txt");

        //Проверяем что строка из файла содержит данные
        if (Objects.requireNonNull(stringWithLogs).isEmpty()) {
            try {
                throw new Exception("File with log is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Разбиваем строку с логом на массив сообщений
        SplitLog splitLog = new SplitLog();
        String[] arrayOfLogs = splitLog.toSplitLogs(stringWithLogs);

        //Разбираем сообщения для коммутации
        ParseCommutationMessages parseCommutationMessages = new ParseCommutationMessages();
        String[] arrayOfCommutationMessages = parseCommutationMessages.getCommutationMessageFromLog(arrayOfLogs);


        //Выводим список коммутации
        Arrays.stream(arrayOfCommutationMessages).forEach(System.out::println);

    }


}
