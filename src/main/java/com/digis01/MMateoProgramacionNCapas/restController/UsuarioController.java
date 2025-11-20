package com.digis01.MMateoProgramacionNCapas.restController;

import com.digis01.MMateoProgramacionNCapas.DAO.UsuarioJPADAOImplementacion;
import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import com.digis01.MMateoProgramacionNCapas.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioJPADAOImplementacion usuarioJPADAOImplementacion;

    @GetMapping
    public ResponseEntity GetAll() {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementacion.GetAll();
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

    @GetMapping("{idUsuario}")
    public ResponseEntity GetById(@PathVariable("idUsuario") int idUsuario) {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementacion.GetById(idUsuario);;
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

    @PostMapping
    public ResponseEntity Add(@RequestBody UsuarioJPA usuario) {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementacion.Add(usuario);
            // result.status = 201;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

    @PutMapping("{idUsuario}")
    public ResponseEntity Update(@RequestBody UsuarioJPA usuario, @PathVariable("idUsuario") int idUsuario) {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementacion.Update(usuario, idUsuario);
            // result.status = 201;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

//    @PatchMapping("imagen/{idUsuario}")
//    public ResponseEntity UpdateImagen(@RequestBody UsuarioJPA usuario) {
//        Result result = new Result();
//        try {
//            result = usuarioJPADAOImplementacion.Update(usuario);
//           // result.status = 201;
//        } catch (Exception ex) {
//            result.correct = false;
//            result.errorMessage = ex.getLocalizedMessage();
//            result.ex = ex;
//            result.status = 500;
//        }
//        return ResponseEntity.status(result.status).body(result);
//    }
    @DeleteMapping("{idUsuario}")
    public ResponseEntity Delete(@PathVariable("idUsuario") int idUsuario) {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementacion.Delete(idUsuario);
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

    @GetMapping("{seach}")
    public ResponseEntity GetById(@RequestBody UsuarioJPA usuario) {
        Result result = new Result();
        try {
            result = usuarioJPADAOImplementacion.GetAllDinamico(usuario);
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
