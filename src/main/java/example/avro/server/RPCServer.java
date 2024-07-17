package example.avro.server;

public interface RPCServer {
    void start() throws InterruptedException;
    void stop();
}
