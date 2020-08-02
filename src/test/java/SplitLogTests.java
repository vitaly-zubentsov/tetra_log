import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class SplitLogTests extends TestBase {

    /**
     * Проверка что входные логи правильно разбиваются на сообщения
     */
    @Test
    public void splitLogTest() throws FileNotFoundException {


        String logsFromFile = downloadLogsFromFile("src/test/resources/commutation/logWithCommutationMessages.txt");

        String[] arrayOfLogs = splitLog(logsFromFile);

        //Проверяем, что количество сообщений совпадает с ожидаемым
        Assert.assertEquals(67, arrayOfLogs.length);


        StringBuilder stringBuilder = new StringBuilder();

        for (String s : arrayOfLogs) {
            stringBuilder.append(s);
        }

        String logsFromProgram = stringBuilder.toString();

        //Проверяем, что логи совпадают
        Assert.assertEquals(logsFromFile, logsFromProgram);
    }

    /**
     * Проверка что входные логи правильно разбиваются на при наличии пустого файла
     */
    @Test
    public void splitEmptyLogTest() throws FileNotFoundException {


        String logsFromFile = downloadLogsFromFile("src/test/resources/commutation/emptyLog.txt");

        String[] arrayOfLogs = splitLog(logsFromFile);

        //Проверяем, что количество сообщений совпадает с ожидаемым
        Assert.assertEquals(1, arrayOfLogs.length);


        StringBuilder stringBuilder = new StringBuilder();

        for (String s : arrayOfLogs) {
            stringBuilder.append(s);
        }

        String logsFromProgram = stringBuilder.toString();

        //Проверяем, что логи совпадают
        Assert.assertEquals(logsFromFile, logsFromProgram);
    }
}
