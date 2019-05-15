package com.ltt.logintest.service;

import com.ltt.logintest.model.Staff;
import com.ltt.logintest.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return (ArrayList<Staff>) staffRepository.findAll();
    }

    @Override
    public List<Staff> searchByProperties(String name, Date startDate, Date endDate, String phonenumber, String address) {
        return staffRepository.searchByProperties(name, startDate, endDate, phonenumber, address);
    }

    @Override
    public Staff getStaffById(long id) {
        Staff obj = staffRepository.findById(id).get();
        return obj;
    }

    @Override
    public List<Staff> getStaffByName(String name) {
        ArrayList<Staff> listStaff = new ArrayList<>();
        staffRepository.findByName(name).forEach(e -> listStaff.add(e));
        return listStaff;
    }

    @Override
    public void updateStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(long id) {
        staffRepository.delete(getStaffById(id));
    }

    @Override
    public void saveStaff(Staff staff) {
        staffRepository.save(staff);
    }

}
