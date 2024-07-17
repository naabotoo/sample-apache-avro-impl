package example.avro;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.avro.ipc.netty.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.avro.client.UserConsumer;
import example.avro.domains.users.UserProto;
import example.avro.server.StartRPCServer;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    static final int CONNECT_TIMEOUT_MILLIS = 2000; 

    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        StartRPCServer.init().startRPCServerSingleThread();

        InetSocketAddress socketAddress = new InetSocketAddress(9090);
        NettyTransceiver client = null;

        try {
            client = new NettyTransceiver(socketAddress, CONNECT_TIMEOUT_MILLIS, new UserConsumer());
        } catch (Exception e){
            LOGGER.warn("error occurred while setting up client message : "+ e.getMessage());
        }

        if(client != null){
            boolean isConnected = client.isConnected();


            LOGGER.info("is client connected  : "+ isConnected);
            UserProto userProxy = (UserProto) SpecificRequestor.getClient(UserProto.class, new SpecificRequestor(UserProto.class, client));

    
        System.out.println("Client built, got proxy");

        // fill in the Message record and send it
        // Message message = new Message(null, null, null, null);
        // message.setTo(new Utf8(args[0]));
        // message.setFrom(new Utf8(args[1]));
        // message.setBody(new Utf8(args[2]));
        // System.out.println("Calling proxy.send with message:  " + message.toString());
        LOGGER.info("Result: " + userProxy.add("John Doe", 16));

        // cleanup
        System.out.println("Closing client");
        client.close(true);
        }
    }
}
