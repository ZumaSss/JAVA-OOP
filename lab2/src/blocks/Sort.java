package blocks;

import Exceptions.WrongParamsQuantity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;

import static blocks.Block.InOutParam.IN_OUT;

public class Sort extends Block{
    private static final Logger logger = LogManager.getLogger(Sort.class.getName());

    @Override
    public InOutParam getParamOfBlock() {
        return IN_OUT;
    }

    @Override
    public void exec(ArrayList<String> text, ArrayList<String> args) throws WrongParamsQuantity {
        logger.trace("Execution is started!");
        if (args.size() != 0) {
            throw new WrongParamsQuantity();
        }

        text.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        logger.trace("Execution completed!");
    }
}
