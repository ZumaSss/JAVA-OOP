package blocks;

import Exceptions.WrongParamsQuantity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static blocks.Block.InOutParam.IN_OUT;

public class Replace extends Block{
    private static final Logger logger = LogManager.getLogger(Replace.class.getName());

    @Override
    public InOutParam getParamOfBlock() {
        return IN_OUT;
    }

    @Override
    public void exec(ArrayList<String> text, ArrayList<String> args) throws WrongParamsQuantity {
        logger.trace("Execution is started!");
        if (args.size() != 2) {
            throw new WrongParamsQuantity();
        }
        String wordToReplace = args.get(0);
        String wordToPlace = args.get(1);

        for (int i = 0; i < text.size(); i++) {
            text.set(i, text.get(i).replaceAll(wordToReplace, wordToPlace));
        }
        logger.trace("Execution completed!");
    }
}
