package co.edu.uptc.jpa.repositories;

import co.edu.uptc.jpa.entity.CategoriaEntity;
import co.edu.uptc.jpa.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPARepositoryCategoria extends CrudRepository<CategoriaEntity/* change for adapter model */, String>, QueryByExampleExecutor<CategoriaEntity/* change for adapter model */> {
}
