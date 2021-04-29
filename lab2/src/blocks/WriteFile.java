package blocks;

import Exceptions.WrongParamsQuantity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

import static blocks.Block.InOutParam.OUT;

public class WriteFile extends Block {
    private static final Logger logger = LogManager.getLogger(WriteFile.class.getName());

    @Override
    public InOutParam getParamOfBlock() {
        return OUT;
    }

    @Override
    public void exec(ArrayList<String> text, ArrayList<String> args) throws WrongParamsQuantity, FileNotFoundException {
        logger.trace("Execution is started!");
        if (args.size() != 1) {
            throw new WrongParamsQuantity();
        }
        PrintWriter writer = new PrintWriter(
                                new OutputStreamWriter(
                                    new FileOutputStream("output")));
        for (String s : text) {
            writer.write(s + '\n');
        }

        writer.close();

        logger.trace("Execution completed!");
    }
}
