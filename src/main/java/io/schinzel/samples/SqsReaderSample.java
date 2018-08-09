package io.schinzel.samples;

import com.amazonaws.regions.Regions;
import io.schinzel.awsutils.sqs.SqsMessage;
import io.schinzel.awsutils.sqs.SqsReader;
import io.schinzel.basicutils.configvar.ConfigVar;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class SqsReaderSample {

    public static void main(String[] args) {
        String awsSqsAccessKey = ConfigVar.create(".env").getValue("AWS_SQS_ACCESS_KEY");
        String awsSqsSecretKey = ConfigVar.create(".env").getValue("AWS_SQS_SECRET_KEY");
        //Create reader. Can be reused if desirable.
        SqsReader sqsReader = SqsReader.builder()
                .awsAccessKey(awsSqsAccessKey)
                .awsSecretKey(awsSqsSecretKey)
                .queueName("my_first_queue.fifo")
                .region(Regions.EU_WEST_1)
                .build();
        //Get a message from the queue. This will make the message invisible.
        SqsMessage message = sqsReader.getMessage();
        //Get the body from the message and do something with it
        String body = message.getBody();
        //Delete the message from the queue
        message.deleteMessageFromQueue();
    }
}