package co.edu.uptc.r2dbc.repository.producto;

import co.edu.uptc.model.categoria.Categoria;
import co.edu.uptc.model.producto.Producto;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import co.edu.uptc.model.productvariant.ProductVariant;
import co.edu.uptc.r2dbc.entity.ProductoEntity;
import co.edu.uptc.r2dbc.entity.ProductoRepMapper;
import co.edu.uptc.r2dbc.helper.ReactiveAdapterOperations;
import co.edu.uptc.r2dbc.repository.categoria.MyReactiveRepositoryAdapter;
import co.edu.uptc.r2dbc.repository.productoVariante.ProductoVarianteRepositoryAdapter;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MyReactiveRepositoryAdapter categoriaRepositoryAdapter;
    @Autowired
    private ProductoVarianteRepositoryAdapter productoVariantRepositoryAdapter;

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


        return repository.findById(idProducto)
                .flatMap(entity -> buscarCategoriaPorId(entity.getIdCategoria())
                        .flatMap(categoria -> productoRepMapper.toModel(Mono.just(entity), categoria))
                );
    }

    @Override
    public Flux<Producto> buscarProductos() {
        return repository.findAll()
                .flatMap(entity -> buscarCategoriaPorId(entity.getIdCategoria())
                        .flatMap(categoria -> productoRepMapper.toModel(Mono.just(entity), categoria))
                );
    }

    @Override
    public Mono<Producto> actualizarProducto(Producto producto) {
        return repository.findById(producto.getId())
                .flatMap(entity -> {
                    entity.setNombre(producto.getNombre());
                    entity.setDescripcion(producto.getDescripcion());
                    entity.setColor(producto.getColor());
                    entity.setPrecio(producto.getPrecio());
                    entity.setIsActive(producto.isActive());
                    return repository.save(entity);
                })
                .flatMap(entity -> buscarCategoriaPorId(entity.getIdCategoria())
                        .flatMap(categoria -> productoRepMapper.toModel(Mono.just(entity), categoria))
                );
    }

    @Override
    public Mono<Void> eliminarProducto(Integer idProducto) {

        return repository.findById(idProducto)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("El producto con ID " + idProducto + " no existe.")))
                .flatMap(producto -> repository.deleteById(idProducto));
    }


    private Mono<Categoria> buscarCategoriaPorId(Integer idCategoria) {
        return categoriaRepositoryAdapter.findById(idCategoria)
                .map(entity -> new Categoria(entity.getId(), entity.getNombre()));
    }


}
