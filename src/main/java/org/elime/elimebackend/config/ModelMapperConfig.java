package org.elime.elimebackend.config;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.*;
import org.elime.elimebackend.data.entities.*;
import org.elime.elimebackend.data.enumerators.Role;
import org.elime.elimebackend.data.repository.*;
import org.elime.elimebackend.security.exception.customException.MissingEntityIdException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final PasswordEncoder passwordEncoder;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final CreamRepository creamRepository;
    private final DoughRepository doughRepository;
    private final ShapeRepository shapeRepository;
    private final DecorationRepository decorationRepository;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, String> passwordConverter = context -> passwordEncoder.encode(context.getSource());

        Converter<String, ProductCategory> productCategoryIdToProductCategoryConverter = context -> {
            String id = context.getSource();
            if(id == null) throw new MissingEntityIdException("Id della categoria mancante");

            return productCategoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria non trovata"));
        };

        Converter<String, Product> productIdToProductConverter = context -> {
            String id = context.getSource();
            if(id == null) throw new MissingEntityIdException("Id del prodotto mancante");

            return productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));
        };

        Converter<String, Cream> creamIdToCreamConverter = context -> {
            String id = context.getSource();
            if(id == null) throw new MissingEntityIdException("Id della crema mancante");

            return creamRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Crema non trovata"));
        };

        Converter<String, Dough> doughIdToDoughConverter = context -> {
            String id = context.getSource();
            if(id == null) throw new MissingEntityIdException("Id della pasta mancante");

            return doughRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Pasta non trovata"));
        };

        Converter<String, Shape> shapeIdToShapeConverter = context -> {
            String id = context.getSource();
            if(id == null) throw new MissingEntityIdException("Id della forma mancante");

            return shapeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Forma non trovata"));
        };

        Converter<String, Decoration> decorationIdToDecorationConverter = context -> {
            String id = context.getSource();
            if(id == null) throw new MissingEntityIdException("Id della decorazione mancante");

            return decorationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Decorazione non trovata"));
        };


        // mapping per creare un admin
        modelMapper.addMappings(new PropertyMap<AdminCreateDto, Admin>() {
            @Override
            protected void configure() {
                map().setRole(Role.ROLE_ADMIN);
                using(passwordConverter).map(source.getPassword(), destination.getPassword());
            }
        });

        //mapping per creare un customer
        modelMapper.addMappings(new PropertyMap<CustomerCreateDto, Customer>() {
            @Override
            protected void configure() {
                map().setRole(Role.ROLE_USER);
                using(passwordConverter).map(source.getPassword(), destination.getPassword());
            }
        });

        //mapping per creare un product
        modelMapper.addMappings(new PropertyMap<ProductCreateDto, Product>() {
            @Override
            protected void configure() {
                skip().setImgUrl(null);
                using(productCategoryIdToProductCategoryConverter).map(source.getCategoryId(), destination.getCategory());
            }
        });

        //mapping per creare un order
        modelMapper.addMappings(new PropertyMap<OrderCreateDto, Order>() {
            @Override
            protected void configure() {
                skip().setPaymentMethod(null);
                skip().setUser(null);
                skip().setOrderItems(null);
            }
        });

        //mapping per creare un orderItem
        modelMapper.addMappings(new PropertyMap<OrderItemCreateDto, OrderItem>() {
            @Override
            protected void configure() {
                skip().setOrder(null);
                skip().setProduct(null);
                skip().setCustomization(null);
                skip().setOrderItemsComponent(null);
                using(productIdToProductConverter).map(source.getProductId(), destination.getProduct());
            }
        });

        //mapping per creare un orderItemCustomization
        modelMapper.addMappings(new PropertyMap<OrderItemCustomizationCreateDto, OrderItemCustomization>() {
            @Override
            protected void configure() {
                skip().setOrderItem(null);
                skip().setImgUrl(null);
                using(creamIdToCreamConverter).map(source.getCreamId(), destination.getCream());
                using(doughIdToDoughConverter).map(source.getDoughId(), destination.getDough());
                if(source.getShapeId() != null)
                    using(shapeIdToShapeConverter).map(source.getShapeId(), destination.getShape());
                else skip().setShape(null);

                if(source.getDecorationId() != null)
                    using(decorationIdToDecorationConverter).map(source.getDecorationId(), destination.getDecoration());
                else skip().setDecoration(null);
            }
        });

        //mapping per creare un orderItemComponent
        modelMapper.addMappings(new PropertyMap<OrderItemComponentCreateDto, OrderItemComponent>() {
            @Override
            protected void configure() {
                skip().setOrderItem(null);
                using(productIdToProductConverter).map(source.getProductId(), destination.getProduct());
            }
        });

        // todo configurazione modelMapper

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PUBLIC);


        return modelMapper;
    }
}
