import model.CommutationLogMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCommutationMessages {

    //Паттерн для поиска сообщений коммутации в массиве сообщений
    private final Pattern SEARCHING_COMMUTATION_MESSAGES = Pattern.compile("0\\d\\.\\d{7}, \\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}:\\n.\\*\\[....],E1_CONNECTION-...: CH/CE=.\\d TCH=.\\d m=\\d{4}\\n.*Передача от: CE=.\\d um=.... Id=\\d{4}\\n.*Прием от\\.\\.\\.: CE=.\\d um=.... Id=\\d{4}\\n.*");

    //Паттерн для подсчёта количества строк
    private final Pattern COUNT_NUMBER_OF_STRING =
            Pattern.compile("\n");

    //Паттерны для поиска необходимых полей в строке
    private final Pattern SEARCHING_ID_WITH_DATE =
            Pattern.compile("0\\d\\.\\d{7}, \\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}:\\d{2}");
    private final Pattern SEARCHING_CE_NUMBER =
            Pattern.compile("(?<=CH/CE=)\\d{2}");
    private final Pattern SEARCHING_TIME_SLOT =
            Pattern.compile("(?<=TCH=)\\s*\\d+");
    private final Pattern SEARCHING_ARRAY_OF_RECEIVER_CE_NUMBER =
            Pattern.compile("(?<=CE рассылки: ).*\\d");
    private final Pattern SEARCHING_RECEIVER_CE_NUMBER =
            Pattern.compile("(?<=Передача от: CE=)\\s*\\d+");
    private final Pattern SEARCHING_RECEIVER_UM =
            Pattern.compile("(?<=Передача от: CE=...um= ) \\d*");
    private final Pattern SEARCHING_RECEIVER_ID =
            Pattern.compile("(?<=Передача от:.CE=...um=.....Id=)\\d{4}");
    private final Pattern SEARCHING_SENDER_CE =
            Pattern.compile("(?<=Прием от...:.CE=)\\s*\\d*");
    private final Pattern SEARCHING_SENDER_UM =
            Pattern.compile("(?<=Прием от...:.CE=...um=)\\s*\\d*");
    private final Pattern SEARCHING_SENDER_ID =
            Pattern.compile("(?<=Прием от...:.CE=...um=.....Id=)\\d{4}");

    public String[] getCommutationMessageFromLog(String[] arrayOfLogs) {
        return Arrays.stream(arrayOfLogs).
                filter(SEARCHING_COMMUTATION_MESSAGES.asPredicate()).
                toArray(String[]::new);
    }

    public CommutationLogMessage[] getArrayOfObjectWithCommutationData(String[] arrayOfCommutationMessages) {

        Collection<CommutationLogMessage> arrayOfObjectsWithCommutationData = new ArrayList<>();
        for (String stringWithCommutationMessage : arrayOfCommutationMessages) {

            //Инициализируем переменный и проверяем их корректность, если данные не корректны то завершаем обрабоку
            //данного сообщения с логами
            String idWithDate =
                    findStringFromCommutationMessages(SEARCHING_ID_WITH_DATE, stringWithCommutationMessage);
            if (idWithDate.isEmpty()) {
                continue;
            }

            //Проверка корректности данных о начале и конце коммутации, если данных нет завершаем итерацию цикла
            if (!(Pattern.matches("[\\s\\S]*E1_CONNECTION-Off[\\s\\S]*", stringWithCommutationMessage) ||
                    Pattern.matches("[\\s\\S]*E1_CONNECTION- On[\\s\\S]*", stringWithCommutationMessage))) {
                continue;
            }
            boolean isStartConnection = Pattern.matches("[\\s\\S]*E1_CONNECTION- On[\\s\\S]*", stringWithCommutationMessage);

            int ceNumber =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_CE_NUMBER, stringWithCommutationMessage));
            if (ceNumber == 0) {
                continue;
            }

            int timeSlot =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_TIME_SLOT, stringWithCommutationMessage));
            if (timeSlot == 0) {
                continue;
            }

            //В зависимости от того, присутствует ли строка "CE рассылки" обрабатываем по разному receiverCE
            int[] receiverCE;
            int numberOfStringInMessage = countOfMessagesInString(stringWithCommutationMessage);
            if (numberOfStringInMessage == 5) {
                receiverCE =
                        convertStringToArrayOfInt(
                                findStringFromCommutationMessages(
                                        SEARCHING_ARRAY_OF_RECEIVER_CE_NUMBER,
                                        stringWithCommutationMessage));
            } else if (numberOfStringInMessage == 4) {
                receiverCE = new int[]{convertStringToInt(findStringFromCommutationMessages(
                        SEARCHING_RECEIVER_CE_NUMBER,
                        stringWithCommutationMessage))};
            } else {
                receiverCE = new int[]{0};
            }
            if ((receiverCE[0] == 0)) {
                continue;
            }

            int receiverUM =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_RECEIVER_UM, stringWithCommutationMessage));
            if (receiverUM == 0) {
                continue;
            }

            int receiverID =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_RECEIVER_ID, stringWithCommutationMessage));
            if (receiverID == 0) {
                continue;
            }

            int senderCE =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_SENDER_CE, stringWithCommutationMessage));
            if (senderCE == 0) {
                continue;
            }

            int senderUM =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_SENDER_UM, stringWithCommutationMessage));
            if (senderUM == 0) {
                continue;
            }

            int senderID =
                    convertStringToInt(
                            findStringFromCommutationMessages(SEARCHING_SENDER_ID, stringWithCommutationMessage));
            if (senderID == 0) {
                continue;
            }

            //Если данные корректны и мы попали в эту строку, то создаем объект
            arrayOfObjectsWithCommutationData.add(new CommutationLogMessage(
                            idWithDate,
                            isStartConnection,
                            ceNumber,
                            timeSlot,
                            receiverCE,
                            receiverUM,
                            receiverID,
                            senderCE,
                            senderUM,
                            senderID
                    )
            );
        }
        return arrayOfObjectsWithCommutationData.
                toArray(new CommutationLogMessage[arrayOfObjectsWithCommutationData.size()]);
    }

    /**
     * Подсчёт количества строк в сообщении коммутации
     */
    private int countOfMessagesInString(String stringWithCommutationMessage) {
        int numberOfString = 0;
        Matcher matcher = COUNT_NUMBER_OF_STRING.matcher(stringWithCommutationMessage);
        while (matcher.find()) numberOfString++;
        return numberOfString;
    }

    /**
     * Поиск подстроки данных для параметра receiverCE
     */
    private int[] convertStringToArrayOfInt(String stringWithArrayOfInt) {

        String[] arrayOfString = stringWithArrayOfInt.split(",");
        int[] arrayOfInt = new int[arrayOfString.length];
        for (int i = 0; i < arrayOfString.length; i++) {
            arrayOfInt[i] = convertStringToInt(arrayOfString[i]);

            //Если получаем 0 (ошибка данных), то отдаём массив int с нулём
            //чтобы далее не смогли создать объект с невалидными данными
            if (arrayOfInt[i] == 0) {
                return new int[]{0};
            }
        }
        return arrayOfInt;
    }

    /**
     * Поиск подстроки с данными, с последующим приведением строки к int
     */
    private int convertStringToInt(String stringWithInt) {
        try {
            return Integer.parseInt(stringWithInt.replace(" ", ""));
        } catch (NumberFormatException e) {
            //если ловим ошибку неправильного формата данных, то передаём дальше 0, чтобы далее не смогли создать
            //объект c невалидными данными
            return 0;
        }
    }

    /**
     * Поиск подстроки с данными
     */
    private String findStringFromCommutationMessages(Pattern patternForSearching, String stringWithCommutationMessage) {
        Matcher matcher = patternForSearching.matcher(stringWithCommutationMessage);
        if (matcher.find()) {
            return stringWithCommutationMessage.substring(matcher.start(), matcher.end());
        } else {
            return "";
        }
    }

}
