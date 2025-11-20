
package com.digis01.MMateoProgramacionNCapas.DAO;

import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import com.digis01.MMateoProgramacionNCapas.JPA.UsuarioJPA;
import java.util.List;

public interface IUsuarioJPA {
    Result GetAll();
    Result Add(UsuarioJPA usuario);
    Result Update(UsuarioJPA usuario, int idUsuario);
    Result UpdateImagen(int idUsuario, String imagen);
    Result GetAllDinamico(UsuarioJPA usuario);
    Result GetById(int idUsuario);
    Result Delete(int idUsuario);
    Result SaveAll(List<UsuarioJPA> usuarios);
}
