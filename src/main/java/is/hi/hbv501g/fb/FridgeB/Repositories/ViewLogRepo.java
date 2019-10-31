package is.hi.hbv501g.fb.FridgeB.Repositories;

import is.hi.hbv501g.fb.FridgeB.Entities.ViewLog;

import java.util.List;

public interface ViewLogRepo {
    ViewLog save(ViewLog viewLog);
    void delete(ViewLog viewLog);
    List<ViewLog> findAll();
}
