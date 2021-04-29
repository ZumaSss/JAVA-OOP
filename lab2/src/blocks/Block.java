package blocks;

import Exceptions.WrongParamsQuantity;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Block {
    public abstract InOutParam getParamOfBlock();

    public enum InOutParam {
        DEFAULT,
        IN,
        OUT,
        IN_OUT
    }



    public abstract void exec(ArrayList<String> text, ArrayList <String> args) throws WrongParamsQuantity, IOException;
}
