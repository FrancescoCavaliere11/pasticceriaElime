package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.ProductCreateDto;
import org.elime.elimebackend.data.entities.Product;
import org.elime.elimebackend.data.repository.ProductRepository;
import org.elime.elimebackend.service.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createProduct(ProductCreateDto productCreateDto) {
        // todo creazione e generazione dell'immagine
        productRepository.save(modelMapper.map(productCreateDto, Product.class));
    }
}
