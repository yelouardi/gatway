package com.humanup.matrix.management.api.gatway.bs.impl.listner;

import com.humanup.matrix.management.api.gatway.dao.RoleDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import com.humanup.matrix.management.api.gatway.vo.RoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RefreshScope
public class RabbitMQRoleListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQRoleListner.class);
  @Autowired
  private RoleDAO roleDAO;
  @RabbitListener(queues = {"${role.queue.name}"})
  public void receive(RoleVO roleVO) {
    try {
      LOGGER.info("Receive  message... {} ", roleVO.toString());
      Role roleToSave = Role.builder()
              .roleTitle(roleVO.getRoleTitle())
              .roleDescription(roleVO.getRoleDescription())
              .build();
        roleDAO.save(roleToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
