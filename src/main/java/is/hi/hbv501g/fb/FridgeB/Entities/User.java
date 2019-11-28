package is.hi.hbv501g.fb.FridgeB.Entities;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String UName;
    private String password;
    private boolean admin;

    public User() {
    }

    public User(String UName, String password, boolean admin) {
        this.UName = UName;
        this.password = password;
        this.admin = admin;
    }

    public User(String UName, String password) {
        this.UName = UName;
        this.password = password;
        this.admin = false;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return UName;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}