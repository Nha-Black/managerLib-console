package com.home.control;

import java.util.ArrayList;
import java.util.List;

import com.home.component.Cursor;
import com.home.dao.AccountDao;
import com.home.models.Account;
import com.home.view.LoginView;

public class LoginControl {
    private List<Account> accounts;
    private LoginView view;

    public LoginControl() {
        init();
        Account acc = new Account();
        while(true){
            acc = view.control();//lấy user và pass
            if (checkAccount(acc)) break;
            else{
                view.mess("tài khoản không tồn tại");
            }
        }
        Cursor.clear();
        System.out.println("đăng nhập thành công");
    }
    private void init(){
        accounts = new ArrayList<>();
        accounts = AccountDao.read();
        view = new LoginView();
    }

    public boolean checkAccount(Account account) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(account.getUsername()) &&
                acc.getPassword().equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
