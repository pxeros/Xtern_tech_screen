/**
 * Created by Peter on 10/26/2016.
 */

import java.util.Random;

public class Server {
    private int randomNumber;
    private Random r = new Random();
    private int numUsers;
    private int maxUser;
    User[] users;

    //this asigns a random integer to the next
    public Server(int maxUser) {
        this.randomNumber = r.nextInt(100);
        this.numUsers = 0;
        this.maxUser = maxUser;
        this.users = new User[maxUser];
    }

    public User findUser(String stringID) {
        if (stringID == null || stringID.length() == 0){
            return null;
        }
        for (int i = 0; i < this.numUsers; i++) {
            if (users[i].getStringID().equals(stringID)) {
                return users[i];
            }
        }
        return null;
    }

    //This is function number 1,
    public boolean HandleGuess(String stringID, int guess) throws NullUserException {
        User currentUser = findUser(stringID);
        if (currentUser == null) {
            throw new NullUserException("Error: User does not exist\n");
        }
        if (guess == randomNumber) {
            currentUser.setXternCoin(currentUser.getXternCoin() + 1);
            return true;
        } else {
            return false;
        }
    }

    public int GetCoins(String stringID) throws NullUserException {
        User currentUser = findUser(stringID);
        if (currentUser == null) {
            throw new NullUserException("Error: User does not exist\n");
        }
        return currentUser.getXternCoin();
    }

    //This is a function
    public int generateUser() {
        if (numUsers >= maxUser) {
            System.out.printf("User buffer full: no new users can be added on this server\n");
            return -1;
        } else {
            users[numUsers++] = new User(Integer.toString(r.nextInt(10000)));
            System.out.printf("New User %s has been added to the server\n", users[numUsers - 1].getStringID());
            return 0;
        }
    }

    public void StartGuessing() {
        int user;
        if (generateUser() == 0) {
            user = numUsers - 1;
        } else {
            user = r.nextInt(numUsers - r.nextInt(numUsers));
        }
        if (user == numUsers) {
            user--;
        }
        User currentUser = this.users[user];
        try {
            for (int i = 0; i < 100; i++) {
                boolean check = HandleGuess(currentUser.getStringID(), r.nextInt(100));
            }
            System.out.printf("User %s now has %d coins\n", currentUser.getStringID(), GetCoins(currentUser.getStringID()));
        } catch (NullUserException e) {
            System.out.printf(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server(15);
        for (int i = 0; i < server.maxUser; i++){
            server.users[i] = new User(Integer.toString(server.r.nextInt(900)));
        }
        server.numUsers = server.maxUser;
        for (int i = 0; i < 20; i++) {
            server.StartGuessing();
        }
    }
}
