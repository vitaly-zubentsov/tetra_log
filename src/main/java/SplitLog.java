public class SplitLog {

     /**
     * Разбиваем строку на массив строк, для разбиения используем регулярное выражение поиска идентификатора сообщения
     */

    public String[] toSplitLogs(String stringWithLogs) {
        return stringWithLogs.split("(?=0\\d\\.\\d{7},)", -1);
    }
}
