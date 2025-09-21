package org.elime.elimebackend.service.interfaces;

import org.elime.elimebackend.data.dto.create.ProductCategoryCreateDto;

public interface ProductCategoryService {
    void createProductCategory(ProductCategoryCreateDto productCategoryCreateDto);
}
