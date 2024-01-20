package com.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driver.model.Admin;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Map<Integer, Admin> adminDB = new HashMap<>();
//    public String registerAdmin(Admin admin) {
//        int adminId = admin.getAdminId();
//        this.adminDB.put(adminId, admin);
//        return "Admin registration successfully";
//    }

}
