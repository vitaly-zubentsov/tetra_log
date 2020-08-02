import model.CommutationLogMessage;

import java.io.FileNotFoundException;

/**
 * Базовый класс тестов, содержит методы используемые другими классами тестов
 */
public class TestBase {


    /**
     * Создание объектов, содержащих сообщение коммутации из массива сообщений с коммутацией
     */
    CommutationLogMessage[] getArrayOfObjectsWithCommutationData(String pathToFileWithLogs) throws FileNotFoundException {

        String[] arrayOfCommutationMessages = getCommutationMessagesFromFile(pathToFileWithLogs);

        //создаём массив объектов сообщений коммутации из строки
        ParseCommutationMessages parseCommutationMessages = new ParseCommutationMessages();
        return parseCommutationMessages.getArrayOfObjectWithCommutationData(arrayOfCommutationMessages);
    }

    /**
     * Получение массива сообщений только с логами коммутации из общего массива сообщений
     */
    String[] getCommutationMessagesFromFile(String pathToFileWithLogs) throws FileNotFoundException {

        String stringWithLogs = downloadLogsFromFile(pathToFileWithLogs);

        String[] arrayOfLogs = splitLog(stringWithLogs);

        //Разбираем сообщения для коммутации
        ParseCommutationMessages parseCommutationMessages = new ParseCommutationMessages();
        return parseCommutationMessages.getCommutationMessageFromLog(arrayOfLogs);
    }

    /**
     * Разбиение строки логов на массив сообщений
     */
    String[] splitLog(String stringWithLogs) {
        //Разбиваем строку с логом на массив сообщений
        SplitLog splitLog = new SplitLog();
        return splitLog.toSplitLogs(stringWithLogs);
    }

    /**
     * Загрузка файла из лога и преобразование лога в строку
     */
    String downloadLogsFromFile(String pathToFileWithLogs) throws FileNotFoundException {
        //Загружаем логи из файла
        Loader loader = new Loader();
        return loader.downloadLogFromFile(pathToFileWithLogs);
    }


}
