package is.hi.hbv501g.fb.FridgeB.Services.Implementations;

import is.hi.hbv501g.fb.FridgeB.Entities.ViewLog;
import is.hi.hbv501g.fb.FridgeB.Services.ViewLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewLogServiceImp implements ViewLogService {
    ViewLogServiceImp repository;

    @Autowired
    public ViewLogServiceImp(ViewLogServiceImp repository) {
        this.repository = repository;
    }

    @Override
    public ViewLog save(ViewLog viewLog) {
        return repository.save(viewLog);
    }

    @Override
    public void delete(ViewLog viewLog) {
        repository.delete(viewLog);
    }

    @Override
    public List<ViewLog> findAll() {
        return repository.findAll();
    }
}
