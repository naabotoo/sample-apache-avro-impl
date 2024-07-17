package example.avro.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartRPCServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(StartRPCServer.class);

    final RPCServerImpl serverImpl;
    final ExecutorService executorService;

    public static StartRPCServer init(){
        return new StartRPCServer();
    }

    private StartRPCServer(){
        this.serverImpl = new RPCServerImpl();
        this.executorService = Executors.newWorkStealingPool();
    }

    public void startRPCServerSingleThread(){
        try {
            serverImpl.start();
        } catch (Exception e){
            LOGGER.warn("error occured while starting avro server. message : "+ e.getMessage());
        }
    } 

    public void startRPCServer(){
        try {
            this.executorService.submit(new Runnable() {
                @Override
                public void run(){
                    try {
                        serverImpl.start();
                    } catch (Exception e){
                        LOGGER.warn("error occured while starting avro server. message : "+ e.getMessage());
                    }
                }
            });
        } catch (Exception e){
            LOGGER.warn("error occurred while running executing task. message : "+ e.getMessage());
        }
    }
}
