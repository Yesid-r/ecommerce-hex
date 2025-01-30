package co.edu.uptc.jpa.repositories;

import co.edu.uptc.jpa.entity.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface JPARepositoryCategoria extends CrudRepository<CategoriaEntity/* change for adapter model */, Integer>, QueryByExampleExecutor<CategoriaEntity/* change for adapter model */> {

    Optional<CategoriaEntity> findById(Integer id);
}
