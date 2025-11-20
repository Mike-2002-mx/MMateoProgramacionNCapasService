package com.digis01.MMateoProgramacionNCapas.DAO;

import com.digis01.MMateoProgramacionNCapas.JPA.Result;

public interface IColoniaJPADAO {
    Result GetByIdMunicipio(int idMunicipio);
    Result GetByCodigoPostal(String codigoPostal);
}
