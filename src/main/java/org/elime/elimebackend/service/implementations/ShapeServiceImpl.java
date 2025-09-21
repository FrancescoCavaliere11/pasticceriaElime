package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.ShapeCreateDto;
import org.elime.elimebackend.data.entities.Shape;
import org.elime.elimebackend.data.repository.ShapeRepository;
import org.elime.elimebackend.service.interfaces.ShapeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createShape(ShapeCreateDto shapeCreateDto) {
        shapeRepository.save(modelMapper.map(shapeCreateDto, Shape.class));
    }
}
