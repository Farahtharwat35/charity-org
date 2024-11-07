package com.charity_org.demo.Models.repository;
import com.charity_org.demo.Models.User;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
