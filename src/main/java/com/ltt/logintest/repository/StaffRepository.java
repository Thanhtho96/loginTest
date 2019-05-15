package com.ltt.logintest.repository;

import com.ltt.logintest.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Long> {
    List<Staff> findByName(String name);

//    @Query("from Staff st where st.name like %:name% " +
//            "and (:startDate is null or st.birthday >=:startDate) and (:endDate is null or st.birthday <= :endDate) " +
//            "and st.phonenumber like %:phonenumber% and st.address like %:address%")
    @Query(value = "select * from staff where name like %:name%" +
            "and (:startDate is null birthday >= :startDate)" +
            "and (:endDate is null birthday <= :endDate)" +
            "and phonenumber like :phonenumber and address like :address", nativeQuery = true)
    List<Staff> searchByProperties(@Param("name") String name,
                                   @Param("startDate") Date birthday,
                                   @Param("endDate") Date endDate,
                                   @Param("phonenumber") String phonenumber,
                                    @Param("address") String address);

    @Query("from Staff st where st.name like %:name% " +
            "and (concat(st.birthday, '') like %:birthday%) and st.phonenumber like %:phonenumber% and st.address like %:address%")
    List<Staff> searchByProperties(@Param("name") String name,
                                   @Param("birthday") String birthday,
                                   @Param("phonenumber") String phonenumber,
                                   @Param("address") String address);

//    @Query("from StaffEntity st where st.name like %:name% " +
//            "and st.phonenumber like %:phonenumber% and st.address like %:address%")
//    List<StaffEntity> searchNotBirthday(@Param("name") String name,
//                                         @Param("phonenumber") String phonenumber,
//                                         @Param("address") String address);
}
