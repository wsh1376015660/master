package domain;

public class Teacher {
    private String username;
    private String password;
    private String cID;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public Teacher(String username, String password, String cID) {
        this.username = username;
        this.password = password;
        this.cID = cID;
    }
}
