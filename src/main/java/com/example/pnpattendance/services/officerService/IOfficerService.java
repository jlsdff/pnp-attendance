package com.example.pnpattendance.services.officerService;

import com.example.pnpattendance.models.Officer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IOfficerService {

    Officer getOfficer(long id);

}
