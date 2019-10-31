package is.hi.hbv501g.fb.FridgeB.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ViewLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

    public Date fromdate;
    public Date todate;

    public ViewLog() {
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getToDate() {
        return todate;
    }

    public void setToDate(Date todate) {
        this.todate = todate;
    }


    public ViewLog(Recipe recipe, User user, Date fromdate, Date todate) {
        this.recipe = recipe;
        this.user = user;
        this.fromdate = fromdate;
        this.todate = todate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}