import java.io.FileNotFoundException;

public class TestBase {

    String[] getCommutationMessagesFromFile(String pathToFileWithLogs) throws FileNotFoundException {

        String stringWithLogs = downloadLogsFromFile(pathToFileWithLogs);

        String[] arrayOfLogs = splitLog(stringWithLogs);

        //Разбираем сообщения для коммутации
        ParseCommutationMessages parseCommutationMessages = new ParseCommutationMessages();
        return parseCommutationMessages.getCommutationMessageFromLog(arrayOfLogs);
    }

    String[] splitLog(String stringWithLogs) {
        //Разбиваем строку с логом на массив сообщений
        SplitLog splitLog = new SplitLog();
        return splitLog.toSplitLogs(stringWithLogs);
    }

    String downloadLogsFromFile(String pathToFileWithLogs) throws FileNotFoundException {
        //Загружаем логи из файла
        Loader loader = new Loader();
        return loader.downloadLogFromFile(pathToFileWithLogs);
    }


}
