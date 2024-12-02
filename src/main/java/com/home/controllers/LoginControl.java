package control;

import models.Account;
import java.util.List;

public class LoginControl {
    private List<Account> accounts;

    public LoginControl(List<Account> accounts) {
        this.accounts = accounts;
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
