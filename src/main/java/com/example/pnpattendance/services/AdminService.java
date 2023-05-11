package com.example.pnpattendance.services;


import com.example.pnpattendance.models.Admin;
import com.example.pnpattendance.repositories.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminService implements IAdminService{

    @Autowired
    private final IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin createAdmin(Admin admin) {

        return adminRepository.save(admin);

    }

    @Override
    public Admin updateAdmin(long id, Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> getAdmin(long adminId) {

        return adminRepository.findById(adminId)
                .stream()
                .findFirst();
    }

    @Override
    public void deleteAdmin(long adminId) {
        adminRepository.deleteById(adminId);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
