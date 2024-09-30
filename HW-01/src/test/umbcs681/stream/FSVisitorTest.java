package umbcs681.stream;

import umbcs681.stream.fs.*;
import umbcs681.stream.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class FSVisitorTest{

    private static FileSystem fs;
    private static Directory repo;
    private static Directory src;
    private static Directory main;
    private static Directory test;
    private static File readme;
    private static File A;
    private static File B;
    private static File ATest;
    private static File BTest;
    private static Link rm;
    private LocalDateTime currentTime = TestFixtureInitializer.currentTime;

    @BeforeAll
    public static void setUpFS(){
        fs = TestFixtureInitializer.createFS();
        repo = fs.getRootDirs().getFirst();
        src = repo.getSubDirectories().getFirst();
        main = src.getSubDirectories().getFirst();
        test = src.getSubDirectories().get(1);
        readme = repo.getFiles().getFirst();
        A = main.getFiles().getFirst();
        B = main.getFiles().get(1);
        ATest = test.getFiles().getFirst();
        BTest = test.getFiles().get(1);
        rm = test.getLink().getFirst();
    }


    @Test
    //Test Case 1: Functional Test: Verify CountingVisitor implementation
    public void verify_CountingVisitor(){
        CountingVisitor visitor = new CountingVisitor();
        repo.accept(visitor);
        assertEquals(4, visitor.getDirNum());
        assertEquals(5, visitor.getFileNum());
        assertEquals(1, visitor.getLinkNum());
    }

    @Test
    //Test Case 2: Functional Test: Verify FileCrawlingVisitor implementation
    public void verify_FileCrawlingVisitor(){
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        repo.accept(visitor);
        LinkedList<File> actual =  visitor.getFiles();
        assertTrue(actual.contains(readme));
        assertTrue(actual.contains(A));
        assertTrue(actual.contains(B));
        assertTrue(actual.contains(ATest));
        assertTrue(actual.contains(BTest));
        assertFalse(actual.contains(main));
        assertFalse(actual.contains(rm));
        assertFalse(actual.contains(test));
        assertFalse(actual.contains(src));
    }

    @Test
    //Test Case 3: Functional Test: Verify files() stream implementation
    public void verify_StreamFileCrawlingVisitor(){
        LocalDateTime time = LocalDateTime.of(2024, 1, 1, 0, 0);
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        repo.accept(visitor);
        long count = visitor.files().filter((File file)->file.getcreationTime().isAfter(time)).filter(file -> file.getName().endsWith(".java")).count();
        assertEquals(3, count);
    }

    @Test
    //Test Case 4: Functional Test: Verify FileSearchVisitor implementation
    public void verify_FileSearchVisitor(){
        FileSearchVisitor visitor = new FileSearchVisitor("readme.md");
        repo.accept(visitor);
        assertEquals(1, visitor.getFoundFiles().size());
        assertSame(readme, visitor.getFoundFiles().getFirst());

        FileSearchVisitor visitor1 = new FileSearchVisitor("B.java");
        repo.accept(visitor1);
        assertEquals(1, visitor1.getFoundFiles().size());
        assertSame(B, visitor1.getFoundFiles().getFirst());
    }

}