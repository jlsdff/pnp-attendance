package com.example.pnpattendance.repositories;

import com.example.pnpattendance.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends CrudRepository<Admin, Long> {

    Admin findByEmail(String email);

}
