import model.CommutationLogMessage;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ParseCommutationMessages {

    private final Pattern PATTERN_FOR_ALL_SEARCHING_COMMUTATION_MESSAGES = Pattern.compile("E1_CONNECTION-");

    public String[] getCommutationMessageFromLog(String[] arrayOfLogs) {
        return Arrays.stream(arrayOfLogs).filter(PATTERN_FOR_ALL_SEARCHING_COMMUTATION_MESSAGES.asPredicate()).toArray(String[]::new);
    }

    public CommutationLogMessage[] getArrayOfObjectWithCommutationData(String[] arrayOfCommutationMessages) {
        //TODO реализовать разбор сообщений

        return new CommutationLogMessage[]{new CommutationLogMessage(
                "афыва",
                true,
                1,
                1,
                new int[]{1, 2, 3},
                1,
                1,
                1,
                1,
                1
        )};
    }

}
