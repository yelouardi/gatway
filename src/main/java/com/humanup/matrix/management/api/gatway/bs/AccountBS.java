package com.humanup.matrix.management.api.gatway.bs;


import com.humanup.matrix.management.api.gatway.vo.AccountVO;

import java.util.List;

public interface AccountBS {

    boolean createAccount(AccountVO Account);
    AccountVO findAccountByLastName(String accountLastName);
    AccountVO findAccountByMailAdresse(String accountMailAdresse);
    List<AccountVO> findListAccount();
    List<AccountVO> findListRolesByRoleTitle(String roleTitle);

}
