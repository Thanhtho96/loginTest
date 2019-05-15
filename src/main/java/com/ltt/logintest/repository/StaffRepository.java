package com.ltt.logintest.repository;

import com.ltt.logintest.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Long> {
    List<Staff> findByName(String name);

    @Query("from Staff st where st.name like %:name% " +
            "and ( st.birthday >= :startDate) " +
            "and ( st.birthday <= :endDate) " +
            "and st.phonenumber like %:phonenumber% and st.address like %:address%")
    List<Staff> searchByProperties(@Param("name") String name,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   @Param("phonenumber") String phonenumber,
                                    @Param("address") String address);

    @Query("from Staff st where st.name like %:name% " +
            "and (concat(st.birthday, '') like %:birthday%) and st.phonenumber like %:phonenumber% and st.address like %:address%")
    List<Staff> searchByProperties(@Param("name") String name,
                                   @Param("birthday") String birthday,
                                   @Param("phonenumber") String phonenumber,
                                   @Param("address") String address);

}
