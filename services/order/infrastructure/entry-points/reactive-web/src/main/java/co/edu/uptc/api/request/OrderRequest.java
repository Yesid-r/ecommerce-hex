package co.edu.uptc.api.request;

import java.util.List;

public record OrderRequest (
        String id,
        String date,
        String customerId,
        List<Integer> products
) {


}
