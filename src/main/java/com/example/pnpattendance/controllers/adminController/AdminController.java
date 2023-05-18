package com.example.pnpattendance.controllers.adminController;

import com.example.pnpattendance.response.Response;
import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.services.adminService.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping()
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {

        try{
            if(admin.getEmail().isEmpty() || admin.getPassword().isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            Admin newAdmin = adminService.createAdmin(admin);
            return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public Admin updateAdmin(@PathVariable(value = "id") long id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }

    @GetMapping(path = "/{id}")
    public Response getAdmin(@PathVariable(value = "id") long id) {

        Optional<Admin> admin = adminService.getAdmin(id);

        if (admin.isPresent()) {
            return new Response("Admin found", "Success", admin.get());
        }

        return new Response("Admin not found", "Failed", null);
    }

    @DeleteMapping(path = "/{id}")
    public int deleteAdmin(@PathVariable(value = "id") long id) {
        adminService.deleteAdmin(id);
        return 1;
    }

    @GetMapping(path = "/email")
    public Response getAdminByEmail(@RequestBody Admin admin) {

        String email = admin.getEmail();

        System.out.println(email);

        Admin foundAdmin = adminService.getAdminByEmail(email);

        if(foundAdmin == null){
            return new Response("Wrong email", "Failed", null);
        }
        return new Response("Admin found", "Success", foundAdmin);
    }
}
