package com.ltt.logintest.service;

import com.ltt.logintest.model.Staff;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IStaffService {
    List<Staff> getAllStaff();

    List<Staff> searchByProperties(@Param("name") String name,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   @Param("phonenumber") String phonenumber,
                                   @Param("address") String address);

    Staff getStaffById(long id);

    List<Staff> getStaffByName(String name);

    void updateStaff(Staff staff);

    void deleteStaff(long id);

    void saveStaff(Staff staff);
}
