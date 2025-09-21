package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.ProductCategoryCreateDto;
import org.elime.elimebackend.data.entities.ProductCategory;
import org.elime.elimebackend.data.repository.ProductCategoryRepository;
import org.elime.elimebackend.service.interfaces.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createProductCategory(ProductCategoryCreateDto productCategoryCreateDto) {
        productCategoryRepository.save(modelMapper.map(productCategoryCreateDto, ProductCategory.class));
    }
}
