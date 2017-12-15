package de.fynder.m4b_tagger;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NativeExecutableCheckerTest {
    private Runtime runtime;
    private NativeExecutableChecker subject;
    private Process process;
    private String[] command = new String[]{"ffmpeg"};
    @Before
    public void setUp() {
//        when(test.getUniqueId()).thenReturn(43);

        runtime = mock(Runtime.class);
        process = mock(Process.class);
        subject = new NativeExecutableChecker(runtime);
    }

    @Test
    public void executableExistsTrue() throws IOException {
        when(runtime.exec(command)).thenReturn(process);
        when(process.exitValue()).thenReturn(0);
        assertTrue(subject.executableExists(command));
    }

    @Test
    public void executableExistsFalse() throws IOException {

        when(runtime.exec(command)).thenThrow(Exception.class);
        assertFalse(subject.executableExists(command));
    }

    @Test
    public void executableExistsWrongExitCode() throws IOException {
        when(runtime.exec("ffmpeg")).thenReturn(process);
        when(process.exitValue()).thenReturn(1);
        assertFalse(subject.executableExists(command));
    }
}
