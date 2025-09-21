package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.DecorationCreateDto;
import org.elime.elimebackend.data.entities.Decoration;
import org.elime.elimebackend.data.repository.DecorationRepository;
import org.elime.elimebackend.service.interfaces.DecorationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecorationServiceImpl implements DecorationService {
    private final ModelMapper modelMapper;
    private final DecorationRepository decorationRepository;

    @Override
    public void createDecoration(DecorationCreateDto decorationCreateDto) {
        decorationRepository.save(modelMapper.map(decorationCreateDto, Decoration.class));
    }
}
