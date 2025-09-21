package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.DoughCreateDto;
import org.elime.elimebackend.data.entities.Dough;
import org.elime.elimebackend.data.repository.DoughRepository;
import org.elime.elimebackend.service.interfaces.DoughService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoughServiceImpl implements DoughService {
    private final DoughRepository doughRepository;
    private final ModelMapper modelMapper;


    @Override
    public void createDough(DoughCreateDto doughCreateDto) {
        doughRepository.save(modelMapper.map(doughCreateDto, Dough.class));
    }
}
