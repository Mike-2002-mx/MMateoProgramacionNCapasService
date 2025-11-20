package com.digis01.MMateoProgramacionNCapas.DAO;

import com.digis01.MMateoProgramacionNCapas.JPA.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaJPADAOImplementacion implements IColoniaJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByIdMunicipio(int idMunicipio) {
        Result result = new Result();

        try {

            TypedQuery queryColonia = entityManager.createQuery("FROM ColoniaJPA c WHERE c.Municipio.IdMunicipio = :idMunicipio", ColoniaJPA.class).
                    setParameter("idMunicipio", idMunicipio);
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            
            result.object = colonias;
            result.correct=true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
