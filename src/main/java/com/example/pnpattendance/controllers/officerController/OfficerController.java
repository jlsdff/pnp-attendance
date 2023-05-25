package com.example.pnpattendance.controllers.officerController;


import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.models.Officer;
import com.example.pnpattendance.services.officerService.IOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/officer")
@CrossOrigin(origins = "http://localhost:3000")
public class OfficerController {

    @Autowired
    private final IOfficerService officerService;

    public OfficerController(IOfficerService officerService){
        this.officerService = officerService;
    }


    @GetMapping(path = "{id}")
    public Officer getOfficer(@PathVariable(value = "id") long id){

        Officer officer =  officerService.getOfficer(id);

        if(officer == null){
            return new Officer();
        }
        return officer;
    }

    @GetMapping(path = "/all")
    public Iterable getAll(){
        return officerService.getAll();
    }



}
