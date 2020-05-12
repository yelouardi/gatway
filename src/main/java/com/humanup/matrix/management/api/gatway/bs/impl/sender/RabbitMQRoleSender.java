package com.humanup.matrix.management.api.gatway.bs.impl.sender;

import com.humanup.matrix.management.api.gatway.vo.RoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQRoleSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQRoleSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQRoleSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${role.queue.name}")
  String queueName;

  public void send(RoleVO roleVO) {
    LOGGER.info("Sending message... {} ", roleVO.toString());
    rabbitTemplate.convertAndSend(queueName, roleVO);
  }
}
