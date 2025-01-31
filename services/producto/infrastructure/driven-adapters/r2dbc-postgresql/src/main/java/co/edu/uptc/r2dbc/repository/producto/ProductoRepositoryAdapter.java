package co.edu.uptc.r2dbc.repository.producto;

import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import co.edu.uptc.r2dbc.entity.ProductoEntity;
import co.edu.uptc.r2dbc.entity.ProductoRepMapper;
import co.edu.uptc.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductoRepositoryAdapter extends ReactiveAdapterOperations<
        Producto,

        ProductoEntity,
        Integer,
        ProductoRepositoryR2DBC

        >
implements ProductoRepository
{
    private final ProductoRepMapper productoRepMapper;

    public ProductoRepositoryAdapter(ProductoRepositoryR2DBC repository, ObjectMapper mapper, ProductoRepMapper productoRepMapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Producto.class));
        this.productoRepMapper = productoRepMapper;
    }

    @Override
    public Mono<Producto> guardarProducto(Producto producto) {
        return repository.save(productoRepMapper.toEntity(producto))
                .flatMap(entity -> productoRepMapper.toModel(Mono.just(entity), producto.getCategoria()));
    }

    @Override
    public Mono<Producto> buscarPorId(Integer idProducto) {
        return this.repository.findById(idProducto).map(this::toEntity);
    }

    @Override
    public Flux<Producto> buscarProductos() {
        return this.repository.findAll().map(this::toEntity);
    }
}
