package com.humanup.matrix.management.api.gatway.bs.impl.listner;

import com.humanup.matrix.management.api.gatway.dao.AccountDAO;
import com.humanup.matrix.management.api.gatway.dao.RoleDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Account;
import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
public class RabbitMQAccountListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQAccountListner.class);

  @Autowired
  private AccountDAO accountDAO;
  @Autowired
  private RoleDAO roleDAO;

  @RabbitListener(queues = { "${account.queue.name}" })
  public void receive(AccountVO accountVO) {
    try {
      LOGGER.info("Receive  message... {} ", accountVO.toString());
      Role role = roleDAO.findByRoleTitle(accountVO.getAccountRole());

      if (null == role) {
        LOGGER.info("Received message as generic: {}", accountVO.toString());
      }
      Account accountToSave = Account.builder()
              .password(accountVO.getPassword())
              .accountMailAdresse(accountVO.getAccountMailAdresse())
              .accountFirstName(accountVO.getAccountFirstName())
              .accountLastName(accountVO.getAccountLastName())
              .role(role)
              .build();
      accountDAO.save(accountToSave);
    }catch(Exception ex){
      LOGGER.info("Error  message... {} ", ex.getMessage(),ex);

    }
  }
}

