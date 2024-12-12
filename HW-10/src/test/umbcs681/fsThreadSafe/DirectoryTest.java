package umbcs681.fsThreadSafe;
import umbcs681.fsThreadSafe.fs.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DirectoryTest{

    private static FileSystem fs;
    private static RunnableDirectory repo;
    private static RunnableDirectory src;
    private static RunnableDirectory main;
    private static RunnableDirectory test;
    private static RunnableFile readme;
    private static RunnableFile A;
    private static RunnableFile B;
    private static RunnableFile ATest;
    private static RunnableFile BTest;
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
    }

    private String[] dirToStringArray(RunnableDirectory d){
        String[] dirInfo = { d.getName(), String.valueOf(d.getSize()), String.valueOf(d.getcreationTime())};
        return dirInfo;
    }

    @Test
    //Test Case 1: Functional Test: Verify Directory Equality Check for directory "repo"
    public void verifyDirectoryEqualityCheck_repo(){
        String[] expected = {"repo", "0", String.valueOf(currentTime)};
        assertTrue(repo instanceof RunnableDirectory);
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
        LinkedList<FSElement> actual = test.getChildren();
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
        LinkedList<RunnableDirectory> actual = repo.getSubDirectories();
        assertEquals(3, actual.size());
        assertTrue(actual.contains(main));
        assertTrue(actual.contains(test));
        assertFalse(actual.contains(A));
        assertFalse(actual.contains(B));
    }

    @Test
    //Test Case 5: Structural Test: Verify getFiles() and getTotalSize() methods
    public void verifyDirectory_getFiles_getTotalSize(){
        LinkedList<RunnableFile> actual = repo.getFiles();
        assertEquals(70, repo.getTotalSize());
        assertTrue(actual.contains(readme));
        LinkedList<RunnableFile> actual1 = main.getFiles();
        assertEquals(35, main.getTotalSize());
        assertTrue(actual1.contains(A));
        assertTrue(actual1.contains(B));
        assertFalse(actual1.contains(ATest));
        assertFalse(actual1.contains(BTest));
    }
}