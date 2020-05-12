package com.humanup.matrix.management.api.gatway.bs.impl.listner;

import com.humanup.matrix.management.api.gatway.dao.AuthorizationDAO;
import com.humanup.matrix.management.api.gatway.dao.RoleDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Authorization;
import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import com.humanup.matrix.management.api.gatway.vo.AuthorizationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RefreshScope
public class RabbitMQAuthorizationListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQAuthorizationListner.class);

  @Autowired
  private RoleDAO roleDAO;
  @Autowired private AuthorizationDAO authorizationDAO;

  @RabbitListener(queues = {"${authorization.queue.name}"})
  public void receive(AuthorizationVO authorization) {
    try {
      LOGGER.info("Receive  message... {} ", authorization.toString());
      Role role = roleDAO.findByRoleTitle(authorization.getAuthorizationRole());
      if (null == role) {
        LOGGER.info("Received message as generic: {}", authorization.toString());
      }
      Authorization authorizationToSave = Authorization.builder()
              .authorizationTitle(authorization.getAuthorizationTitle())
              .authorizationDescription(authorization.getAuthorizationDescription())
              .authorizationUrl(authorization.getAuthorizationUrl())
              .role(role)
              .build();
        authorizationDAO.save(authorizationToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
