package co.edu.uptc.api.DTO;

import co.edu.uptc.model.categoria.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaMapper {
    public Categoria toCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.id());
        categoria.setNombre(categoriaDTO.nombre());
        return categoria;
    }
    public CategoriaDTO toCategoriaDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(),categoria.getNombre());
    }
}
