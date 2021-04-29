package Factory;

import Exceptions.CommandNotFound;
import blocks.Block;

import java.io.IOException;
import java.util.Properties;

public class Factory {
    private static final Properties config = new Properties();
    private Factory() throws  IOException {
        var configStream = Factory.class.getResourceAsStream("factory.config");
        if (configStream == null) {
            throw new IOException();
        }

        config.load(configStream);
    }

    private static volatile Factory instance;
    public static Factory getInstance() throws IOException {
        if (instance == null) {
            synchronized (Factory.class) {
                if (instance == null) {
                    instance = new Factory();
                }
            }
        }
        return instance;
    }

    public Block getBlock(String command) throws Exception {
        if (!config.containsKey(command)) {
            throw new CommandNotFound("Unrecognized command:" + command);
        }

        Block block;

        try {
            var ClassOfBlock = Class.forName(config.getProperty(command));
            var objectInstance = ClassOfBlock.getDeclaredConstructor().newInstance();
            block = (Block)objectInstance;
        } catch (Exception e) {
            throw new Exception("Unable to create!");
        }

        return block;
    }
}
