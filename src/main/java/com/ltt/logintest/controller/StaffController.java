package com.ltt.logintest.controller;

import com.ltt.logintest.model.Staff;
import com.ltt.logintest.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/")
public class StaffController {
    @Autowired
    private StaffService staffService;

    private ArrayList<Staff> listStaff;

    @GetMapping("/")
    public String index(Model model) {
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username",userDetails.getUsername());
        model.addAttribute("role",userDetails.getAuthorities());
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @RequestMapping("/admin/listStaff")
    public String listStudent(Model model) {
        listStaff = (ArrayList<Staff>) staffService.getAllStaff();
        model.addAttribute("listStaff", listStaff);
        return "listStaff";
    }

    @PostMapping("/admin/addStaff")
    public String addStaff(Staff staff, Model model) {
        model.addAttribute("staff", staff);
        return "addStaff";
    }

    @PostMapping("/admin/saveStaff")
    public String addUser(@Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addStaff";
        }

        staffService.saveStaff(staff);
        model.addAttribute("listStaff", staffService.getAllStaff());
        return "redirect:/admin/listStaff";
    }

    @PostMapping("/admin/editStaff/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Staff staff = staffService.getStaffById(id);
        model.addAttribute("staff", staff);
        return "updateStaff";
    }

    @PostMapping("/admin/updateStaff/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Staff staffEntity,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            staffEntity.setId(id);
            return "updateStaff";
        }
        staffService.updateStaff(staffEntity);
        model.addAttribute("listStaff", staffService.getAllStaff());
        return "redirect:/admin/listStaff";
    }

    @PostMapping("/admin/deleteStaff/{id}")
    public String deleteStaff(@PathVariable("id") long id, Model model) {
        staffService.deleteStaff(id);
        model.addAttribute("listStaff", staffService.getAllStaff());
        return "redirect:/admin/listStaff";
    }

    @PostMapping("/admin/advanceSearch")
    public String getStudent(Model model,
                             @Param("name") String name,
                             @Param("startDate") String startDate,
                             @Param("endDate") String endDate,
                             @Param("phonenumber") String phonenumber,
                             @Param("address") String address) {
        Date startDateTemp = null, endDateTemp = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDateTemp = simpleDateFormat.parse(startDate);
            endDateTemp = simpleDateFormat.parse(endDate);

            if (startDateTemp == null) {
                startDateTemp = simpleDateFormat.parse("1970-01-01");
            }
            else if (endDateTemp == null){
                Date date = new Date();
                endDateTemp = simpleDateFormat.parse(simpleDateFormat.format(date));
            }
        } catch (ParseException e) {
        }

        listStaff = (ArrayList<Staff>) staffService.searchByProperties(
                name, startDateTemp, endDateTemp, phonenumber, address);
        model.addAttribute("listStaff", listStaff);
        return "listStaff";
    }

}
