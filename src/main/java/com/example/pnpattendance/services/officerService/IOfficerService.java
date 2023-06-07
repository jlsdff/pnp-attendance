package com.example.pnpattendance.services.officerService;

import com.example.pnpattendance.models.Officer;

public interface IOfficerService {

    Officer getOfficer(long id); //get officer by id

    Iterable<Officer> getAll(); // get all officers

    Officer addOfficer(Officer officer); // Create

    Officer deleteOfficer(long id); // Delete

    Officer updateOfficer(long id, Officer officer); // Update

    

}
