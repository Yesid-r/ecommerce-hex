package co.edu.uptc.jpa.repositories;

import co.edu.uptc.jpa.entity.CategoriaEntity;

import co.edu.uptc.jpa.helper.AdapterOperations;
import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.categoria.gateways.CategoriaRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class JPARepositoryAdapterCategoria extends AdapterOperations<Categoria, CategoriaEntity, String, JPARepositoryCategoria>
implements CategoriaRepository
{

    public JPARepositoryAdapterCategoria(JPARepositoryCategoria repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Categoria.class/* change for domain model */));
    }


    @Override
    public Mono<Categoria> guardarCategoria(Categoria categoria) {


        return Mono.just(this.toEntity(this.repository.save(this.toData(categoria)))).switchIfEmpty(Mono.error(new Exception("No se puede guardar la categoria")));

    }
}
