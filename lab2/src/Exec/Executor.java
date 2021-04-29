package Exec;

import Exceptions.UnrecognizedID;
import Exceptions.WrongInputFormat;
import Exceptions.WrongParamsQuantity;
import Factory.Factory;
import blocks.Block;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class Executor {
    public Executor(String inputFilename) {
        logger.trace("Creating new workflow environment..");

        this.filename = inputFilename;
        workflowSequence = new ArrayList<>();
        text = new ArrayList<>();

        logger.trace("Workflow environment created successfully!");
    }

    private static final Logger logger = LogManager.getLogger(Executor.class);
    Block.InOutParam prevState = Block.InOutParam.DEFAULT;

    static class Pair<K extends String, V extends ArrayList<String>> {

        K command;
        V arguments;

        public Pair(K first, V second) {
            this.setCommand(first);
            this.setArguments(second);
        }

        public K getCommand() {
            return command;
        }

        public void setCommand(K command) {
            this.command = command;
        }

        public V getArguments() {
            return arguments;
        }

        public void setArguments(V arguments) {
            this.arguments = arguments;
        }
    }

    private ArrayList<String> text;
    private final String filename;

    private ArrayList<Pair<String, ArrayList<String>>> workflowSequence;
    private int IndexInSequence = 0;

    public ArrayList<String> getText() {
        return text;
    }

    String getFilename() {
        return filename;
    }

    ArrayList<Pair<String, ArrayList<String>>> getSequence() {
        return workflowSequence;
    }

    private boolean allSequenceRead() {
        return this.IndexInSequence == workflowSequence.size();
    }

    private String getCurrentKey() {
        return workflowSequence.get(IndexInSequence).getCommand();
    }

    private ArrayList<String> getCurrentArguments() {
        return workflowSequence.get(IndexInSequence).getArguments();
    }

    public void render() throws Exception {
        try {
            logger.trace("Start parsing the data..");
            Parser.parseInput(this);
            logger.trace("Parsing completed successfully!");

            while (!allSequenceRead()) {
                Block newBlock = Factory.getInstance().getBlock(getCurrentKey());
                logger.trace("Instance of:" + newBlock.getClass().getSimpleName() + "has created.");

                checkCurBlock(newBlock);
                newBlock.exec(text, getCurrentArguments());
                IndexInSequence++;
            }
            logger.trace("OK! This session will be terminated.");
        } catch (IOException e) {
            logger.error("An error has occured during reading file or creating buffer!!!");
            logger.error("Process will be terminated.");
        } catch (WrongInputFormat e) {
            logger.error("Raw data has wrong format!");
            logger.error("Process will be terminated.");
        } catch (UnrecognizedID e) {
            logger.error("Workflow sequence contains undefined ID!");
            logger.error("Process will be terminated.");
        } catch (WrongParamsQuantity e) {
            logger.error("In created block passed wrong number of arguments!");
            logger.error("Process will be terminated.");
        } catch (Exception e) {
            logger.error("An error has occured during creating of block!");
            logger.error("Process will be terminated.");
        }
    }

    private void checkCurBlock(Block b) throws WrongInputFormat {
        switch (b.getParamOfBlock()) {
            case IN -> {
                if (IndexInSequence != 0 || prevState != Block.InOutParam.DEFAULT) {
                    throw new WrongInputFormat();
                }
            }
            case OUT -> {
                if (IndexInSequence != workflowSequence.size() || prevState == Block.InOutParam.OUT) {
                    throw new WrongInputFormat();
                }
            }
            case IN_OUT -> {
                if (prevState == Block.InOutParam.OUT) {
                    throw new WrongInputFormat();
                }
            }
        }
    }
}
