package com.example.pnpattendance.services.officerService;


import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OfficerService implements IOfficerService{

    @Autowired
    private OfficerRepository officerRepository;

    public OfficerService(OfficerRepository officerRepository){
        this.officerRepository = officerRepository;
    }

    @Override
    public Officer getOfficer(long id) {
        Optional<Officer> officer = officerRepository.findByBadgeNumber(id);
        return officer.orElse(null);
    }
}
