package com.wyz.helloworld;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by yzwang on 2017/8/29.
 */
public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws IOException, TimeoutException ,InterruptedException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello RabbitMQ World!";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        while(true){
            Double temp = new Random().nextDouble();
            String mess = temp.toString();
            channel.basicPublish("", QUEUE_NAME, null, mess.getBytes());

            System.out.println(" [x] Sent '" + mess + "'");

            Thread.sleep(5000);
        }
        //

    }

}
