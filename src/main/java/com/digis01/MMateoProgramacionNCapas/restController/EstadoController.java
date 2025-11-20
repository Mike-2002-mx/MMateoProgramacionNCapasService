package com.digis01.MMateoProgramacionNCapas.restController;

import com.digis01.MMateoProgramacionNCapas.DAO.EstadoJPADAOImplementacion;
import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/estado")
public class EstadoController {

    @Autowired
    private EstadoJPADAOImplementacion estadoJPADAOImplementacion;

    @GetMapping("{idPais}")
    public ResponseEntity GetByIdPais(@PathVariable("idPais") int idPais) {
        Result result = new Result();
        try {
            result = estadoJPADAOImplementacion.GetByIdPais(idPais);;
            result.correct = true;
            result.status = 200;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }
}
