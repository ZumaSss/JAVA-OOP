package blocks;

import Exceptions.WrongParamsQuantity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static blocks.Block.InOutParam.IN_OUT;

public class Dump extends Block {
    private static final Logger logger = LogManager.getLogger(Dump.class.getName());

    @Override
    public void exec(ArrayList<String> text, ArrayList<String> args) throws WrongParamsQuantity, FileNotFoundException {
        logger.trace("Execution is started!");

        if (args.size() != 1) {
            throw new WrongParamsQuantity();
        }
        String filename = args.get(0);
        PrintWriter printWriter = new PrintWriter(
                                    new OutputStreamWriter(
                                        new FileOutputStream(filename)));
        for (String s : text) {
            printWriter.write(s + '\n');
        }

        printWriter.close();
        logger.trace("Execution completed!");
    }

    @Override
    public InOutParam getParamOfBlock() {
        return IN_OUT;
    }
}
