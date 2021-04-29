package blocks;

import Exceptions.WrongParamsQuantity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static blocks.Block.InOutParam.IN_OUT;

public class Grep extends Block{
    private static final Logger logger = LogManager.getLogger(Grep.class.getName());

    @Override
    public void exec(ArrayList<String> text, ArrayList<String> args) throws WrongParamsQuantity {
        logger.trace("Execution is started!");
        if (args.size() != 1) {
            throw new WrongParamsQuantity();
        }

        ArrayList <String> curLine = new ArrayList<>();
        String wordToFind = args.get(0);

        for (String line : text) {
            if (line.contains(wordToFind)) {
                curLine.add(line);
            }
        }
        text.removeAll(text);
        text.addAll(curLine);

        logger.trace("Execution completed!");
    }

    @Override
    public InOutParam getParamOfBlock() {
        return IN_OUT;
    }
}
