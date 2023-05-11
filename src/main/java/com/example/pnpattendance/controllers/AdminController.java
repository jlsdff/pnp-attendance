package com.example.pnpattendance.controllers;

import com.example.pnpattendance.Response;
import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {

    @Autowired
    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping()
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {

        try{
            Admin newAdmin = adminService.createAdmin(admin);
            return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/update/{id}")
    public Admin updateAdmin(@PathVariable(value = "id") long id, @RequestBody Admin admin) {
        adminService.updateAdmin(id, admin);
        return admin;
    }

    @GetMapping(path = "/{id}")
    public Response getAdmin(@PathVariable(value = "id") long id) {

        Optional<Admin> admin = adminService.getAdmin(id);

        if (admin.isPresent()) {
            return new Response("Admin found", "Success", admin);
        }

        return new Response("Admin not found", "Failed", null);
    }

    @DeleteMapping(path = "/{id}")
    public int deleteAdmin(@PathVariable(value = "id") long id) {
        adminService.deleteAdmin(id);
        return 1;
    }

    @GetMapping(path = "/email/{email}")
    public Admin getAdminByEmail(@PathVariable(value = "email") String email) {
        return adminService.getAdminByEmail(email);
    }
}
