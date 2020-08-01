import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CommutationLogTests extends TestBase {

    /**
     * Проверка загрузки логов из файла содержащего сообщения коммутации,
     * с последующей разбивкой данных на массив строк
     */
    @Test
    public void getMessagesFromLogWithCommutationMessagesTest() throws FileNotFoundException {

        String[] arrayOfCommutationMessages =
                getCommutationMessagesFromFile(
                        "src/test/resources/commutation/logWithCommutationMessages.txt");

        //Проверяем, что количество сообщений совпадает с ожидаемым
        Assert.assertEquals(10, arrayOfCommutationMessages.length);

        String onlyCommutationMessagesFromFile =
                downloadLogsFromFile("src/test/resources/commutation/logWithOnlyCommutationMessages.txt");


        StringBuilder stringBuilder = new StringBuilder();

        for (String s : arrayOfCommutationMessages) {
            stringBuilder.append(s);
        }

        String onlyCommutationMessagesFromProgram = stringBuilder.toString();

        //Проверяем, что логи совпадают
        Assert.assertEquals(onlyCommutationMessagesFromFile, onlyCommutationMessagesFromProgram);
    }

    /**
     * Проверка загрузки логов из файла содержащего только сообщения коммутации,
     * с последующей разбивкой данных на массив строк
     */
    @Test
    public void getMessagesFromLogWithOnlyCommutationMessagesTest() throws FileNotFoundException {

        String[] arrayOfCommutationMessages =
                getCommutationMessagesFromFile(
                        "src/test/resources/commutation/logWithOnlyCommutationMessages.txt");

        //Проверяем, что количество сообщений совпадает с ожидаемым
        Assert.assertEquals(10, arrayOfCommutationMessages.length);

        String onlyCommutationMessagesFromFile =
                downloadLogsFromFile("src/test/resources/commutation/logWithOnlyCommutationMessages.txt");

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : arrayOfCommutationMessages) {
            stringBuilder.append(s);
        }

        String onlyCommutationMessagesFromProgram = stringBuilder.toString();

        //Проверяем, что логи совпадают
        Assert.assertEquals(onlyCommutationMessagesFromFile, onlyCommutationMessagesFromProgram);

    }

    /**
     * Проверка загрузки логов из файла не содержащего сообщения коммутации,
     * с последующей разбивкой данных на массив строк
     */
    @Test
    public void getMessagesFromLogWithoutCommutationMessagesTest() throws FileNotFoundException {

        String[] arrayOfCommutationMessages =
                getCommutationMessagesFromFile(
                        "src/test/resources/commutation/logWithoutCommutationMessages.txt");

        //Проверяем, что количество сообщений совпадает с ожидаемым
        Assert.assertEquals(0, arrayOfCommutationMessages.length);

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : arrayOfCommutationMessages) {
            stringBuilder.append(s);
        }

        String commutationMessagesFromProgram = stringBuilder.toString();

        //Проверяем, что логи совпадают
        Assert.assertEquals("", commutationMessagesFromProgram);


    }


}
