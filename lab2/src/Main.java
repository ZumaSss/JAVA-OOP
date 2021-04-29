import Exec.Executor;
import blocks.Dump;
import blocks.ReadFile;
import blocks.Replace;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) throws Exception {
        Executor executor = new Executor("input.txt");
        executor.render();
    }
}
