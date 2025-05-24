package com.HanglyGroup.HanglyBackend.repositories;

import com.HanglyGroup.HanglyBackend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a.addressId FROM Address a " +
            "WHERE a.cep = :cep " +
            "AND a.city = :city " +
            "AND a.state = :state " +
            "AND a.country = :country " +
            "AND a.street = :street " +
            "AND a.number = :number " +
            "AND a.district = :district")
    Optional<Long> findAddressByParams(@Param("cep") String cep,
                                       @Param("city") String city,
                                       @Param("state") String state,
                                       @Param("country") String country,
                                       @Param("street") String street,
                                       @Param("number") String number,
                                       @Param("district") String district);

}
