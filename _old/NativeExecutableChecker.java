package de.fynder.m4b_tagger;

public class NativeExecutableChecker {

    private final Runtime runtime;

    public NativeExecutableChecker(Runtime rt) {
        runtime = rt;
    }

    public boolean executableExists(String[] executable) {
        try {
            Process proc = runtime.exec(executable);
            proc.waitFor();
            return proc.exitValue() == 0;
        } catch(Exception e) {
            return false;
        }

    }
}
