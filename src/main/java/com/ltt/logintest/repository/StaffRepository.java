package com.ltt.logintest.repository;

import com.ltt.logintest.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Long> {
    List<Staff> findByName(String name);

    @Query(value = "select * from staff st where unaccent(st.name) like %:name% " +
            "and ( st.birthday >= :startDate) " +
            "and ( st.birthday <= :endDate) " +
            "and st.phonenumber like %:phonenumber% and unaccent(st.address) like %:address%", nativeQuery = true)
    List<Staff> searchByProperties(@Param("name") String name,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   @Param("phonenumber") String phonenumber,
                                    @Param("address") String address);

}
