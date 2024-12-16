package umbcs681.ConcurrentQueue;
import umbcs681.ConcurrentQueue.fs.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DirectoryTest{

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
    private LocalDateTime currentTime = TestFixtureInitializer.currentTime;

    @BeforeAll
    public static void setUpFS(){
        fs = TestFixtureInitializer.createFS();
        repo = fs.getRootDirs().peek();
        src = repo.getSubDirectories().peek();
        main = src.getSubDirectories().peek();
        LinkedList<Directory> res = new LinkedList<>(src.getSubDirectories());
        test = res.get(1);
        readme = repo.getFiles().peek();
        A = main.getFiles().peek();
        LinkedList<File> res1 = new LinkedList<>(main.getFiles());
        B = res1.get(1);
        ATest = test.getFiles().peek();
        LinkedList<File> res2 = new LinkedList<>(test.getFiles());
        BTest = res2.get(1);
    }

    private String[] dirToStringArray(Directory d){
        String[] dirInfo = { d.getName(), String.valueOf(d.getSize()), String.valueOf(d.getcreationTime())};
        return dirInfo;
    }

    @Test
    //Test Case 1: Functional Test: Verify Directory Equality Check for directory "repo"
    public void verifyDirectoryEqualityCheck_repo(){
        String[] expected = {"repo", "0", String.valueOf(currentTime)};
        assertTrue(repo instanceof Directory);
        assertArrayEquals(expected, dirToStringArray(repo));
        assertTrue(repo.isDirectory());
    }

    @Test
    //Test Case 2: Functional Test: Verify Directory Equality Check for directory "main"
    public void verifyDirectoryEqualityCheck_main(){
        String[] expected = {"main", "0", String.valueOf(currentTime)};
        assertArrayEquals(expected, dirToStringArray(main));
    }


    @Test
    //Test Case 3: Structural Test: Verify countChildren() and getChildren() methods
    public void verifyDirectory_countChildren_getChildren(){
        ConcurrentLinkedQueue<FSElement> actual = test.getChildren();
        assertEquals(3, test.countChildren());
        assertTrue(actual.contains(ATest));
        assertTrue(actual.contains(BTest));
        assertFalse(actual.contains(A));
        assertFalse(actual.contains(B));
        assertFalse(actual.contains(main));
    }

    @Test
    //Test Case 4: Structural Test: Verify getSubDirectories() method
    public void verifyDirectory_getSubDirectories(){
        ConcurrentLinkedQueue<Directory> actual = repo.getSubDirectories();
        assertEquals(3, actual.size());
        assertTrue(actual.contains(main));
        assertTrue(actual.contains(test));
        assertFalse(actual.contains(A));
        assertFalse(actual.contains(B));
    }

    @Test
    //Test Case 5: Structural Test: Verify getFiles() and getTotalSize() methods
    public void verifyDirectory_getFiles_getTotalSize(){
        ConcurrentLinkedQueue<File> actual = repo.getFiles();
        assertEquals(70, repo.getTotalSize());
        assertTrue(actual.contains(readme));
        ConcurrentLinkedQueue<File> actual1 = main.getFiles();
        assertEquals(35, main.getTotalSize());
        assertTrue(actual1.contains(A));
        assertTrue(actual1.contains(B));
        assertFalse(actual1.contains(ATest));
        assertFalse(actual1.contains(BTest));
    }
}