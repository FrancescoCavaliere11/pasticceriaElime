package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.ProductCreateDto;

public interface ProductService {
    void createProduct(ProductCreateDto productCreateDto);
}
