package com.db.bts.scheduler;

import com.db.bts.model.UserTransactionAmountModel;
import com.db.bts.service.TransactionService;
import com.db.bts.service.UserService;
import com.db.bts.service.impl.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class Scheduler {

    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Scheduled(cron = "${cron.minute.expression}")
    public void updateUserStatus() throws Exception {

        Date prev = new Date();
        prev.setMonth(prev.getMonth()-1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date from = dateFormat.parse(dateFormat.format(prev));
        Date to = dateFormat.parse(dateFormat.format(new Date()));
        logger.info("Running scheduler from {} to {}", from, to);
        List<Integer> goldUsers = new ArrayList<Integer>();
        List<Integer> silverUsers = new ArrayList<Integer>();

        List<UserTransactionAmountModel> results = transactionService.findAmountSum(from, to);
        results.forEach(result -> {
            if(result.getTotalAmount() >= 100000) {
                goldUsers.add(result.getUserId());
            } else {
                silverUsers.add(result.getUserId());
            }
        });
        logger.info("Gold users--> {}", goldUsers);
        logger.info("Silver users--> {}", silverUsers);
        if(!goldUsers.isEmpty()) {
            userService.updateMembershipStatus(goldUsers, "Gold");
        }
        if(!silverUsers.isEmpty()) {
            userService.updateMembershipStatus(silverUsers, "Silver");
        }
    }
}
