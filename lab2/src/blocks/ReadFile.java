package blocks;

import Exceptions.WrongParamsQuantity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

import static blocks.Block.InOutParam.IN;

public class ReadFile extends Block {
    private static final Logger logger = LogManager.getLogger(ReadFile.class.getName());

    @Override
    public InOutParam getParamOfBlock() {
        return IN;
    }

    @Override
    public void exec(ArrayList<String> text, ArrayList <String> args) throws WrongParamsQuantity, IOException {
        logger.trace("Execution is started!");
        if (args.size() != 1) {
            throw new WrongParamsQuantity();
        }

        final int indexOfFilename = 0;
        BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(
                                                new FileInputStream(args.get(indexOfFilename))));

        while (reader.ready()) {
            String currentLine = reader.readLine();
            text.add(currentLine);
        }

        reader.close();
        logger.trace("Execution completed!");
    }
}
