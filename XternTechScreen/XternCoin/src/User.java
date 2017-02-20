/**
 * Created by Peter on 10/26/2016.
 */
public class User {
    private String stringID;
    private int XternCoin;

    public User(String stringID) {
        this.stringID = stringID;
        this.XternCoin = 0;
    }
    void setXternCoin(int XternCoin) {
        this.XternCoin = XternCoin;
    }
    int getXternCoin() {
        return this.XternCoin;
    }
    String getStringID() {
        return this.stringID;
    }
}
