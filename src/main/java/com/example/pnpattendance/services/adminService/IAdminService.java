package com.example.pnpattendance.services.adminService;

import com.example.pnpattendance.models.Admin;

import java.util.Optional;

public interface IAdminService {

    Admin createAdmin(Admin admin);

    Admin updateAdmin(long id, Admin admin);

    Optional<Admin> getAdmin(long adminId);

    void deleteAdmin(long adminId);

    Admin getAdminByEmail(String email);

    Admin getAdminByEmailAndPassword(String email, String password);
}
