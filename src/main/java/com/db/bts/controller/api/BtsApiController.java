package com.db.bts.controller.api;

import com.db.bts.entity.Admin;
import com.db.bts.entity.User;
import com.db.bts.service.impl.AdminServiceImpl;
import com.db.bts.service.impl.UserServiceImpl;
import lombok.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bts/api")
public class BtsApiController {

    Logger logger = LoggerFactory.getLogger(BtsApiController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;
    
    @GetMapping("")
   	public ModelAndView loadLogin(Model model) {
    	
    	model.addAttribute("user", new User());
   		return new ModelAndView("login");
   	}

    

    @PostMapping("/user/sign_in")
    public ModelAndView userSignIn(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse res) throws Exception {
        User user1 = userService.userSignIn(user.getEmail(), user.getPassword());
        //System.out.println(user1.toString());
        req.getSession().setAttribute("user", user1);
        return new ModelAndView("home");
    }

    @GetMapping("/admin/sign_in")
    public ResponseEntity<Admin> adminSignIn(@RequestParam("email") @NonNull String email,
                                             @RequestParam("password") @NonNull String password) throws Exception {
        Admin admin = adminService.adminSignIn(email, password);
        return ResponseEntity.ok().body(admin);
    }
    
    @GetMapping("/user/register")
    public ModelAndView adminRegister(Model model) throws Exception {
    	model.addAttribute("user", new User());
   		return new ModelAndView("register");
    }

}
