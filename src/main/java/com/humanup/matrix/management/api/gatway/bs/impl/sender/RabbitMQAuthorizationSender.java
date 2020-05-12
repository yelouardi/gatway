package com.humanup.matrix.management.api.gatway.bs.impl.sender;

import com.humanup.matrix.management.api.gatway.vo.AuthorizationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQAuthorizationSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQAuthorizationSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQAuthorizationSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${authorization.queue.name}")
  String queueName;

  public void send(AuthorizationVO authorizationVO) {
    LOGGER.info("Sending message... {} ", authorizationVO.toString());
    rabbitTemplate.convertAndSend(queueName, authorizationVO);
  }
}
