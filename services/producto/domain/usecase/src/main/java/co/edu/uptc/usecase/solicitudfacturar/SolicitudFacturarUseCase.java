package co.edu.uptc.usecase.solicitudfacturar;

import co.edu.uptc.model.producto.ProductPurchaseResponse;
import co.edu.uptc.model.producto.ProductoRequest;
import co.edu.uptc.model.producto.gateways.ProductoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SolicitudFacturarUseCase {

    private final ProductoRepository  productoRepository;
    //metodo que recibe una lista de productos request que contiene(idProducto, cantidad) y retorna una lista de productos con el precio de venta
    public List<ProductPurchaseResponse> facturar(List<ProductoRequest> productos) {

        var productIds = productos.stream().map(ProductoRequest::productId).toList();
        var storedProducts = productoRepository.obtenerProductosPorIds(productIds);
        // verificar si los productos existen
        if (storedProducts.count().block() != productos.size()) {
            throw new RuntimeException("No se encontraron todos los productos");
        }
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        // verificar si hay suficiente stock
        for (var product : storedProducts.collectList().block()) {
            var productRequest = productos.stream().filter(p -> p.productId().equals(product.getId())).findFirst().orElseThrow();
            if (productRequest.cantidad() > product.getStock()) {
                throw new RuntimeException("No hay suficiente stock para el producto " + product.getNombre());
            }
            // actualizar el stock
            product.setStock(product.getStock() - productRequest.cantidad());
            productoRepository.actualizarProducto(product).block();
            // agregar el producto a la lista de productos a comprar
            purchaseProducts.add(new ProductPurchaseResponse(product.getId(), product.getNombre(), product.getDescripcion(), productRequest.cantidad(), product.getPrecio().doubleValue()));
        }

        return purchaseProducts;



    }

}
