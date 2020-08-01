package model;

public class CommutationLogMessage extends LogMessage {


    private final short senderCENumber;
    private final short senderTimeslotNumber;
    private final short senderUsageMarker;
    private final short senderID;

    private final short[] receiverCENumber;

    public CommutationLogMessage(short senderCENumber, short senderTimeslotNumber, short senderUsageMarker, short senderID, short[] receiverCENumber) {
        this.senderCENumber = senderCENumber;
        this.senderTimeslotNumber = senderTimeslotNumber;
        this.senderUsageMarker = senderUsageMarker;
        this.senderID = senderID;
        this.receiverCENumber = receiverCENumber;
    }


    public short getSenderCENumber() {
        return senderCENumber;
    }

    public short getSenderTimeslotNumber() {
        return senderTimeslotNumber;
    }

    public short getSenderUsageMarker() {
        return senderUsageMarker;
    }

    public short getSenderID() {
        return senderID;
    }

    public short[] getReceiverCENumber() {
        return receiverCENumber;
    }


}
