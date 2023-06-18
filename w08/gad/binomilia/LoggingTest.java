package gad.binomilia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggingTest {

    private BinomialHeap heap;
    private StudentResult studentResult;
    private ByteArrayOutputStream outputStream;

    /**
     * <p>
     *     Theas test will only pass if you use this logging:
     * </p>
     * <p>
     * For Insert:
     * </p>
     * <p>
     *     Insert: 1 soll in den Haufen {{1,2}, {0}} eingefügt werden:<br>
     *     startInsert(1, {{1,2}, {0}})<br>
     *     Nun wird ein Baum für die 1 erstellt und in den Heap eingefügt:<br>
     *     logIntermediateStep({{1,2}, {0}, {1}})<br>
     *     Als nächstes müssen jeweils zwei Bäume gemerget werden:<br>
     *     logIntermediateStep({{1,2}, {0, 1}})<br>
     *     logIntermediateStep({{0,1,[1,2]}})<br>
     *     Damit wäre das Einfügen abgeschlossen.
     * </p>
     * <p>
     * For Remove:
     * </p>
     * <p>
     *     Remove: Aus dem Haufen {{0,1,[1,2]}, {5}} soll das Minimum entfernt werden:<br>
     *     startDeleteMin({{0,1,[1,2]}, {5}})<br>
     *     Danach wird das Minimum gelöscht und die Kinder davon in den Heap eingefügt:<br>
     *     logIntermediateStep({{1}, {1,2}, {5}})<br>
     *     Und zuletzt müssen wieder die einzelnen Bäume mit gleichen Rang gemergt werden:<br>
     *     logIntermediateStep({{1, 5}, {1,2}})<br>
     *     logIntermediateStep({{1, 5, [1, 2]}}) oder logIntermediateStep({{1, 2, [1, 5]}}), je nachdem wie gemergt wird.
     * </p>
     */



    @BeforeEach
    void init() {
        heap = new BinomialHeap();
        studentResult = new StudentResult();
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
    }

    @Test
    void loggingInsertTest() throws IOException {

        for(int i=0;i<10;i++) {
            heap.insert(i, studentResult);
        }

        String consoleOutput = outputStream.toString();
        String expected = Files.readString(Paths.get("test/gad/binomilia/insertLogging.txt"));
        assertEquals(expected, consoleOutput);
    }

    @Test
    void insertDeleteLoggingTest() throws IOException {
        for(int i=0;i<10;i++) {
            heap.insert(i, studentResult);
        }

        for(int i=10;i>0;i--)
        {
            heap.deleteMin(studentResult);
        }

        String consoleOutput = outputStream.toString();
        String expected = Files.readString(Paths.get("test/gad/binomilia/insertDeleteLoggingTest.txt"));
        assertEquals(expected, consoleOutput);
    }
}
