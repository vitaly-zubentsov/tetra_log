package model;

public class CommutationLogMessage extends LogMessage {



    private final int senderCENumber;
    private final int senderTimeslotNumber;
    private final int senderUsageMarker;
    private final int senderID;

    private final int[] receiverCENumber;

    public CommutationLogMessage(int senderCENumber, int senderTimeslotNumber, int senderUsageMarker, int senderID, int[] receiverCENumber) {
        this.senderCENumber = senderCENumber;
        this.senderTimeslotNumber = senderTimeslotNumber;
        this.senderUsageMarker = senderUsageMarker;
        this.senderID = senderID;
        this.receiverCENumber = receiverCENumber;
    }

    public int getSenderCENumber() {
        return senderCENumber;
    }

    public int getSenderTimeslotNumber() {
        return senderTimeslotNumber;
    }

    public int getSenderUsageMarker() {
        return senderUsageMarker;
    }

    public int getSenderID() {
        return senderID;
    }

    public int[] getReceiverCENumber() {
        return receiverCENumber;
    }


}
