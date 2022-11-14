package domain;

public class Friendship {
    private final int firstUserID;
    private final int secondUserID;

    public int getFirstUserID() {
        return firstUserID;
    }

    public int getSecondUserID() {
        return secondUserID;
    }

    public Friendship(int firstUserID, int secondUserID) {
        this.firstUserID = firstUserID;
        this.secondUserID = secondUserID;
    }
}
