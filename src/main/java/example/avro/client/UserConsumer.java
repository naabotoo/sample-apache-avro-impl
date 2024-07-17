package example.avro.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.socket.SocketChannel;

public class UserConsumer implements Consumer<SocketChannel> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

    @Override
    public void accept(SocketChannel t) {
        SocketAddress socketAddress = new InetSocketAddress(9090);
        t.bind(socketAddress);
        boolean isOpen = t.isOpen();
        LOGGER.info("is open : "+ isOpen);
    }  
}
