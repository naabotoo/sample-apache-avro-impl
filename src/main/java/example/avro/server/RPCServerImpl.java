package example.avro.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.netty.NettyServer;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.avro.domains.users.UserImpl;
import example.avro.domains.users.UserProto;

public class RPCServerImpl implements RPCServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RPCServerImpl.class);

    ExecutorService executorService = Executors.newWorkStealingPool();

    Server server;

    @Override
    public void start() throws InterruptedException {
        InetSocketAddress socketAddress = new InetSocketAddress(9090);

        LOGGER.info("netty rpc server host name : "+ socketAddress.getHostName() +" port : "+ socketAddress.getPort());

        SpecificResponder specificResponder = this.specificResponder();

        server = new NettyServer(specificResponder, socketAddress);
        

        try {
            server.start();
        } catch (Exception e){
            LOGGER.warn("error occurred while starting avro server. message : "+ e.getMessage());
        }
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    SpecificResponder specificResponder(){
        return new SpecificResponder(UserProto.class, new UserImpl());
    }

}
