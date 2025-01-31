package co.edu.uptc.r2dbc.repository.categoria;

import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.categoria.gateways.CategoriaRepository;
import co.edu.uptc.r2dbc.entity.CategoriaEntity;
import co.edu.uptc.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Categoria/* change for domain model */,
        CategoriaEntity/* change for adapter model */,
    Integer,
    MyReactiveRepository
>
implements CategoriaRepository

{
    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Categoria.class/* change for domain model */));
    }

    @Override
    public Mono<Categoria> guardarCategoria(Categoria categoria) {
        //return this.toEntity(this.repository.save(this.toData(categoria));
        return this.save(categoria);

    }

    @Override
    public Mono<Categoria> buscarCategoriaPorId(Integer id) {
        return this.repository.findById(id).map(this::toEntity);
    }
}
