package org.elime.elimebackend.service.implementations;

import lombok.RequiredArgsConstructor;
import org.elime.elimebackend.data.dto.create.AdminCreateDto;
import org.elime.elimebackend.data.entities.Admin;
import org.elime.elimebackend.data.repository.AdminRepository;
import org.elime.elimebackend.service.interfaces.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createAdmin(AdminCreateDto adminCreateDto) {
        adminRepository.save(modelMapper.map(adminCreateDto, Admin.class));
    }
}
