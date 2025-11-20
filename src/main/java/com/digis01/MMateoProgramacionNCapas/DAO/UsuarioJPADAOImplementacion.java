package com.digis01.MMateoProgramacionNCapas.DAO;

import com.digis01.MMateoProgramacionNCapas.JPA.UsuarioJPA;
import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import com.digis01.MMateoProgramacionNCapas.exception.ResourceAlreadyExistsException;
import com.digis01.MMateoProgramacionNCapas.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioJPADAOImplementacion implements IUsuarioJPA {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {

            TypedQuery queryUsuario = entityManager.createQuery("FROM UsuarioJPA ORDER BY IdUsuario", UsuarioJPA.class);
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();
            result.object = usuarios;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetById(int idUsuario) {
        Result result = new Result();
        try {
            UsuarioJPA usuariojpa = entityManager.find(UsuarioJPA.class, idUsuario);
            if (usuariojpa == null) {
                throw new ResourceNotFoundException("Usuario con id: " + idUsuario + " no encontrado");
            }
            result.object = usuariojpa;
            result.correct = true;
            result.status = 200;
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return result;
    }

    @Override
    @Transactional
    public Result Add(UsuarioJPA usuario) {
        Result result = new Result();

        //Encontrar email
        TypedQuery queryEmailUsuario = entityManager.createQuery("FROM UsuarioJPA u WHERE u.Email = :email", UsuarioJPA.class)
                .setParameter("email", usuario.getEmail());

        List<UsuarioJPA> resultsEmail = queryEmailUsuario.getResultList();

        if (!resultsEmail.isEmpty()) {
            throw new ResourceAlreadyExistsException("El email " + usuario.getEmail() + " ya existe en la base de datos");
        }

        //Checar si direcciones existen
        if (usuario.Direcciones != null || usuario.Direcciones.isEmpty()) {
            entityManager.persist(usuario);
            result.correct = true;
            result.status = 201;

        } else {
            usuario.Direcciones.get(0).Usuario = usuario;
            entityManager.persist(usuario);
            result.correct = true;
            result.status = 201;
        }
        return result;
    }

    @Override
    @Transactional
    public Result Update(UsuarioJPA usuario, int idUsuario) {
        Result result = new Result();

        //Encontrar email
        TypedQuery queryEmailUsuario = entityManager.createQuery("FROM UsuarioJPA u WHERE u.Email = :email", UsuarioJPA.class)
                .setParameter("email", usuario.getEmail());

        List<UsuarioJPA> resultsEmail = queryEmailUsuario.getResultList();

        if (!resultsEmail.isEmpty()) {
            throw new ResourceAlreadyExistsException("El email " + usuario.getEmail() + " ya existe en la base de datos");
        }

        UsuarioJPA usuarioExistente = entityManager.find(UsuarioJPA.class, idUsuario);

//            //SettearValores
//            usuario.setPassword(usuarioExistente.getPassword());
//            usuario.setImagen(usuarioExistente.getImagen());
//            if (usuario.Direcciones.size() > 0) {
//                for (DireccionJPA direccionjpa : usuarioExistente.Direcciones) {
//                    Direccion direccion = modelMapper.map(direccionjpa, Direccion.class);
//                    usuario.Direcciones.add(direccion);
//                }
//            }
        usuarioExistente = usuario;
        entityManager.merge(usuarioExistente);
        result.correct = true;
        result.status = 201;

        return result;

    }

    @Override
    @Transactional
    public Result UpdateImagen(int idUsuario, String imagen) {
        Result result = new Result();
        try {

            UsuarioJPA usuariojpa = entityManager.find(UsuarioJPA.class, idUsuario);

            usuariojpa.setImagen(imagen);

            entityManager.merge(usuariojpa);
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;

    }

    @Override
    @Transactional
    public Result Delete(int idUsuario) {
        Result result = new Result();
        try {
            UsuarioJPA usuariojpa = entityManager.find(UsuarioJPA.class, idUsuario);
            entityManager.remove(usuariojpa);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAllDinamico(UsuarioJPA usuario) {
        Result result = new Result();
        try {
            String query = "SELECT u FROM UsuarioJPA u WHERE ";
            result.objects = new ArrayList<>();

            query += "LOWER(u.Nombre) LIKE  '%' || :nombre || '%' AND LOWER(u.ApellidoPaterno) LIKE '%' || :pApellidoPaterno || '%' AND LOWER(u.ApellidoMaterno) LIKE '%' || :pApellidoMaterno|| '%'";

            if (usuario.Rol.getIdRol() > 0) {
                query += " AND u.Rol.IdRol = :pIdRol";
            }

            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery(query, UsuarioJPA.class);

            queryUsuario.setParameter("nombre", usuario.getNombre().toLowerCase());
            queryUsuario.setParameter("pApellidoPaterno", usuario.getApellidoPaterno().toLowerCase());
            queryUsuario.setParameter("pApellidoMaterno", usuario.getApellidoMaterno().toLowerCase());

            if (usuario.Rol.getIdRol() > 0) {
                queryUsuario.setParameter("pIdRol", usuario.Rol.getIdRol());
            }
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();
            result.object = usuarios;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Result SaveAll(List<UsuarioJPA> usuarios) {

        Result result = new Result();
//        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            for (UsuarioJPA usuario : usuarios) {
                //Convertir  ML a JPA
//                entityTransaction.begin();
                entityManager.persist(usuario);
//                entityTransaction.commit();
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
//            entityTransaction.rollback();
        }
        return result;
    }

}
