public class SplitLog {

    /**
     * Разбиваем строку в соотвествии с регулярным выражением для поиска идентификатора сообщения
     * Сам идентификатор отбрасывается, так как для приложения не будет использваться
     * при разбиении образуется совпадение нулевой длины в начале строки
     */

    public String[] toSplitLogs(String stringWithLogs) {
        return stringWithLogs.split("0\\d\\.\\d{5},", -1);
    }
}