package co.edu.uptc.jpa;

import co.edu.uptc.jpa.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPARepository extends CrudRepository<ProductoEntity/* change for adapter model */, String>, QueryByExampleExecutor<ProductoEntity/* change for adapter model */> {
}
