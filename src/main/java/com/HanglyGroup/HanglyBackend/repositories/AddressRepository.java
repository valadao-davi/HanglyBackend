package com.HanglyGroup.HanglyBackend.repositories;

import com.HanglyGroup.HanglyBackend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
