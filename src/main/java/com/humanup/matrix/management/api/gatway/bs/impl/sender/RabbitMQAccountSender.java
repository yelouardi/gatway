package com.humanup.matrix.management.api.gatway.bs.impl.sender;

import com.humanup.matrix.management.api.gatway.vo.AccountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQAccountSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQAccountSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQAccountSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${account.queue.name}")
  String queueName;

  public void send(AccountVO accountVO) {
    rabbitTemplate.convertAndSend(queueName, accountVO);
    LOGGER.info("Sending message... {} ", accountVO.toString());
  }
}
