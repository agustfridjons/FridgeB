package is.hi.hbv501g.fb.FridgeB.Services;

import is.hi.hbv501g.fb.FridgeB.Entities.ViewLog;

import java.util.List;

public interface ViewLogService {
    ViewLog save(ViewLog viewLog);
    void delete(ViewLog viewLog);
    List<ViewLog> findAll();
}
