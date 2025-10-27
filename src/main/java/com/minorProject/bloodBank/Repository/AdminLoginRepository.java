package com.minorProject.bloodBank.Repository;

import com.minorProject.bloodBank.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminLoginRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.userName = :userName")
    Admin findByUsername(@Param("userName") String userName);
}
