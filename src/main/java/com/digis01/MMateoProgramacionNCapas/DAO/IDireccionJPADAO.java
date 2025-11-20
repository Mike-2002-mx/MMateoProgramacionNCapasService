
package com.digis01.MMateoProgramacionNCapas.DAO;

import com.digis01.MMateoProgramacionNCapas.JPA.DireccionJPA;
import com.digis01.MMateoProgramacionNCapas.JPA.Result;

public interface IDireccionJPADAO {
    
    Result AddByIdUsario(DireccionJPA direccion, int idUsuario);
    Result Update(DireccionJPA direccion);
    Result Delete(int idDireccion);
    
}
