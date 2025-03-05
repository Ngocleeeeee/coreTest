package com.example.messcore.staff.repository;

import ezcloud.message.booking.Customer;
import ezcloud.message.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {

    Staff findStaffById(UUID uuid);
}
