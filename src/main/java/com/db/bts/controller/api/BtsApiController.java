package com.db.bts.controller.api;

import com.db.bts.entity.Admin;
import com.db.bts.entity.Audit;
import com.db.bts.entity.User;
import com.db.bts.model.TransactionCancelModel;
import com.db.bts.model.TransactionSearchModel;
import com.db.bts.service.impl.AdminServiceImpl;
import com.db.bts.service.impl.AuditServiceImpl;
import com.db.bts.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bts/api")
public class BtsApiController {

    Logger logger = LoggerFactory.getLogger(BtsApiController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private AuditServiceImpl auditService;
    
    @GetMapping("/logout")
   	public ModelAndView logout(Model model,HttpServletRequest req) {
    	
    	req.getSession().invalidate();
    	model.addAttribute("user", new User());
   		return new ModelAndView("login");
   	}
    
    @GetMapping("")
   	public ModelAndView loadLogin(Model model) {
    	
    	model.addAttribute("user", new User());
   		return new ModelAndView("login");
   	}
    
    @GetMapping("/home")
   	public ModelAndView loadHomePage(Model model, HttpServletRequest req) {
    	
   		return new ModelAndView("home");
   	}
    

    @PostMapping("/user/sign_in")
    public ModelAndView userSignIn(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse res, @RequestParam("login_type") String loginType) throws Exception {
    	
    	if(loginType.equals("user")) {
    		User user1 = userService.userSignIn(user.getEmail(), user.getPassword());
    		req.getSession().setAttribute("user", user1);
    	}
    	if(loginType.equals("admin")){
    		Admin admin1 = adminService.adminSignIn(user.getEmail(), user.getPassword());
    		req.getSession().setAttribute("admin", admin1);
    	}
        
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

    @GetMapping("/about")
    public ModelAndView about() throws Exception {
        return new ModelAndView("about");
    }

    @PostMapping("/cancel")
    public void cancelTransaction(@RequestBody @NonNull TransactionSearchModel transactionSearchModel) throws Exception {
        logger.info("cancellation request");
        TransactionCancelModel transactionCancelModel = new TransactionCancelModel();
        transactionCancelModel.setUserId(transactionSearchModel.getUser().getId());
        transactionCancelModel.setTraderId(transactionSearchModel.getTrader().getId());
        transactionCancelModel.setTransactionId(transactionSearchModel.getId());
        Audit audit = auditService.cancelTransaction(transactionCancelModel);
        logger.info("audit {}", audit);
    }

}
