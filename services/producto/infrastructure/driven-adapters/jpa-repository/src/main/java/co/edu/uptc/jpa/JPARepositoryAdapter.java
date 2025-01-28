package co.edu.uptc.jpa;

import co.edu.uptc.jpa.entity.ProductoEntity;
import co.edu.uptc.jpa.helper.AdapterOperations;
import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class JPARepositoryAdapter extends AdapterOperations<Producto, ProductoEntity, String, JPARepository>
implements ProductoRepository
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Producto.class/* change for domain model */));
    }

    @Override
    public Mono<Producto> guardarProducto(Producto producto) {
        return Mono.just(this.toEntity(this.repository.save(this.toData(producto)))).switchIfEmpty(Mono.error(new Exception("No se puede guardar el producto")));

    }
}
