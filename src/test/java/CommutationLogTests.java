import model.CommutationLogMessage;
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
        Assert.assertEquals(50, arrayOfCommutationMessages.length);

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
        Assert.assertEquals(50, arrayOfCommutationMessages.length);

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


    /**
     * Проверка создания массива объектов коммутационных сообщений из массива строк
     * с валидными данными
     */
    @Test()
    public void commutationLogToModelTest() throws FileNotFoundException {

        CommutationLogMessage[] arrayOfObjectsWithCommutationDataFromProgram = getArrayOfObjectsWithCommutationData("src/test/resources/commutation/logWithOnlyFourCommutationMessages.txt");

        //Проверяем, что количество сообщений совпадает с ожидаемым
        //   Assert.assertEquals(4, arrayOfObjectsWithCommutationDataFromProgram.length);

        //Создаём вручную массив объектов с информацией о коммутации
        // из данных файла logWithThreeCommutationMessages.txt
        CommutationLogMessage[] arrayOfObjectsWithCommutationDataFromLogs =
                new CommutationLogMessage[]{
                        new CommutationLogMessage(
                                "01.1174000, 20.07.2020 08:13:22",
                                false,
                                40,
                                2,
                                new int[]{40},
                                35,
                                100,
                                40,
                                35,
                                100
                        ),
                        new CommutationLogMessage(
                                "01.1174001, 20.07.2020 08:13:23",
                                true,
                                40,
                                2,
                                new int[]{40, 28},
                                29,
                                200,
                                40,
                                29,
                                200
                        ),
                        new CommutationLogMessage(
                                "01.1174002, 20.07.2020 08:13:24",
                                true,
                                12,
                                9,
                                new int[]{12, 28, 17, 14, 5, 6, 13, 2, 1, 7, 40, 11},
                                43,
                                900,
                                12,
                                43,
                                900
                        ),
                        new CommutationLogMessage(
                                "01.1174003, 20.07.2020 08:13:24",
                                false,
                                40,
                                6,
                                new int[]{40, 7, 27, 28},
                                49,
                                600,
                                40,
                                49,
                                600
                        )
                };


        //Проверяем, что логи совпадают
        Assert.assertArrayEquals(arrayOfObjectsWithCommutationDataFromLogs, arrayOfObjectsWithCommutationDataFromProgram);


    }

    /**
     * Проверка создания массива объектов коммутационных сообщений из массива строк
     * при логах не содержащих коммутационные сообщения
     */
    @Test
    public void commutationLogToModelWithoutAnyCommutationMessagesTest() throws FileNotFoundException {

        CommutationLogMessage[] arrayOfObjectsWithCommutationDataFromProgram = getArrayOfObjectsWithCommutationData("src/test/resources/commutation/logWithoutCommutationMessages.txt");

        //Проверяем, что количество сообщений совпадает с ожидаемым
        Assert.assertEquals(0, arrayOfObjectsWithCommutationDataFromProgram.length);

    }


    /**
     * Проверка создания массива объектов коммутационных сообщений из массива строк
     * с не валидными данными.
     * В файле logWithBadCommutationMessages.txt расписаны различные варианты порчи данных логов
     */
    @Test
    public void badCommutationLogToModelTest() throws FileNotFoundException {

        CommutationLogMessage[] arrayOfObjectsWithCommutationDataFromProgram = getArrayOfObjectsWithCommutationData("src/test/resources/commutation/logWithBadCommutationMessages.txt");

        //Проверяем, что количество сообщений совпадает с ожидаемым, одно сообщение из всех валидно
        //Ошибки наличия правильных раздилителей между сообщениями, при условии что сами сообщения соответсвуют шаблону
        //не обрабатываются(ещё 3 сообщения в логе файлов)
        Assert.assertEquals(4, arrayOfObjectsWithCommutationDataFromProgram.length);

    }

}
