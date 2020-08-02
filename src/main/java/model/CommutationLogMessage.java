package model;

public class CommutationLogMessage extends LogMessage {

    //Поля заголовка сообщения

    //Параметр содержащий id сообщения (например, "01.1174054") и дату (например, "20.07.2020 08:13:33")
    //id могут повторяться, поэтому используются вместе как уникальный идентификатор
    private final String idWithDate;

    //True, если в логе "E1_CONNECTION- On" - сообщение коммутации установления соединения
    //False, если в логе "E1_CONNECTION-Off" - сообщение коммутации разрушения соединения
    private final boolean isStartConnection;

    // Значение параметра CH/CE, определяет CE(элемент сети) для которой выдаётся коммутационное сообщение
    private final int ceNumber;

    // Значение параметра TCH, определяет таймслот используемый в CE для данного коммутационного сообщения
    private final int timeSlot;

    // Параметр m - это внутренний параметр сервера и для уровня разбора коммутации в системе не нужен
    // private final int m;


    //Строка "Передача от:", определяет получателя речи (куда коммутируется)

    //Значение параметра CE, определяет CE, получающую речь
    //При наличии строки "CE рассылки" добавляются данные указанные в ней (подробно смотри ниже)
    private final int[] receiverCE;

    //Значение параметра um, назначает usage market, который будет использоваться в CE для данного подключения
    private final int receiverUM;

    //Значение параметра id, назначает identification number, который будет использоваться в CE для данного подключения
    private final int receiverID;


    //Строка "Приём от:", определяет источник речи (откуда коммутируется)

    //Значение параметра CE, определяет CE, отправляющую речь
    private final int senderCE;

    //Значение параметра um, определяет какой usage market у источника речи
    private final int senderUM;

    //Значение параметра id, определяет какой identification number у источника речи
    private final int senderID;


    //Строка CE рассылки, используется когда получателей речи больше чем один для данного коммутационного сообщения.
    // В данной строке отображается только номера CE без полных данных о назначенных в них um и id


    public CommutationLogMessage(
            String idWithDate,
            boolean isStartConnection,
            int ceNumber,
            int timeSlot,
            int[] receiverCE,
            int receiverUM,
            int receiverID,
            int senderCE,
            int senderUM,
            int senderID
    ) {
        this.idWithDate = idWithDate;
        this.isStartConnection = isStartConnection;
        this.ceNumber = ceNumber;
        this.timeSlot = timeSlot;
        this.receiverCE = receiverCE;
        this.receiverUM = receiverUM;
        this.receiverID = receiverID;
        this.senderCE = senderCE;
        this.senderUM = senderUM;
        this.senderID = senderID;
    }

    public String getIdWithDate() {
        return idWithDate;
    }

    public boolean isStartConnection() {
        return isStartConnection;
    }

    public int getCeNumber() {
        return ceNumber;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public int[] getReceiverCE() {
        return receiverCE;
    }

    public int getReceiverUM() {
        return receiverUM;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public int getSenderCE() {
        return senderCE;
    }

    public int getSenderUM() {
        return senderUM;
    }

    public int getSenderID() {
        return senderID;
    }
}
