
package com.digis01.MMateoProgramacionNCapas.restController;

import com.digis01.MMateoProgramacionNCapas.DAO.PaisJPADAOImplementacion;
import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pais")
public class PaisController {
    
    @Autowired
    private PaisJPADAOImplementacion paisJPADAOImplementacion;
    
    @GetMapping
    public ResponseEntity GetAll(){
                Result result = new Result();
        try {
            result = paisJPADAOImplementacion.GetAll();
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
