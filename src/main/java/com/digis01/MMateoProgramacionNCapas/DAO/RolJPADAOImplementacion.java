package com.digis01.MMateoProgramacionNCapas.DAO;

import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import com.digis01.MMateoProgramacionNCapas.JPA.RolJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementacion implements IRolJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery queryRol = entityManager.createQuery("FROM RolJPA", RolJPA.class);
            List<RolJPA> roles = queryRol.getResultList();
            result.object = roles;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
