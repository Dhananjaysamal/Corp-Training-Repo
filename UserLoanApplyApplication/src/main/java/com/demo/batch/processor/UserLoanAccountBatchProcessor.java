package com.demo.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.demo.batch.entity.UserLoanAccountEntity;
import com.demo.bean.User;

public class UserLoanAccountBatchProcessor implements ItemProcessor<User, UserLoanAccountEntity>
{
    public UserLoanAccountEntity process(User user) throws Exception
    {
        System.out.println("Inserting employee : " + user);
        UserLoanAccountEntity userLoanAccount = new UserLoanAccountEntity(user.getUserName(),
        		user.getUserPan(),
        		user.getUserAdhara(),
        		user.getGender(),
        		user.getAge(),
        		user.getUserSalary());
        return userLoanAccount;
    }
    
}
