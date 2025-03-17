package com.example.messcore.customer.repository;

import ezcloud.message.ota.OTA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OtaRepository extends JpaRepository<OTA, UUID> {

}
