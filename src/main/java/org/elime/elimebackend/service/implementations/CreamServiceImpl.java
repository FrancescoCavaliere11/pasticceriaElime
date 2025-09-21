package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.CreamCreateDto;
import org.elime.elimebackend.data.entities.Cream;
import org.elime.elimebackend.data.repository.CreamRepository;
import org.elime.elimebackend.service.interfaces.CreamService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreamServiceImpl implements CreamService {
    private final CreamRepository creamRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createCream(CreamCreateDto creamCreateDto) {
        creamRepository.save(modelMapper.map(creamCreateDto, Cream.class));
    }
}
