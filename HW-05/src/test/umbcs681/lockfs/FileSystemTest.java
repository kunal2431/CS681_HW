package umbcs681.lockfs;
import umbcs681.lockfs.fs.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest{
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

    @Test
    //Test Case 1: Structural Test: Verify getRootDir() method
    public void verifyFileSystem_getRootDir(){
        LinkedList<Directory> rootDirs = fs.getRootDirs();
        assertTrue(rootDirs.contains(repo));
    }

    @Test
    //Test Case 2: Functional Test: Verify Singleton
    public void verifyFileSystem_Singleton(){
        FileSystem cut1 = FileSystem.getFileSystem();
        assertSame(fs, cut1);
    }

    @Test
    //Test Case 3: Structural Test: Verify getParent() method
    public void verifyFileElement_getParent(){
        Directory actual = test.getParent();
        assertSame(src, actual);
    }

    @Test
    //Test Case 4: Structural Test: Verify getSize() method
    public void verifyFileElement_getSize(){
        int actual = ATest.getSize();
        assertEquals(13, actual);
    }

    @Test
    //Test Case 5: Functional Test
    public void verifyFileSystem_SingletonMultipleThreads(){
        FileSystem cut1 = FileSystem.getFileSystem();
        FileSystem cut2 = FileSystem.getFileSystem();
        Thread t1 = new Thread(cut1);
        Thread t2 = new Thread(cut2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertSame(cut1, cut2);
        assertTrue(cut1.getRootDirs().contains(repo));
        assertTrue(cut2.getRootDirs().contains(repo));
        Directory driveE = new Directory(null, "driveE", 0, LocalDateTime.now());
        assertFalse(cut1.getRootDirs().contains(driveE));
        assertFalse(cut2.getRootDirs().contains(driveE));
        cut2.appendRootDir(driveE);
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(cut1.getRootDirs().contains(driveE));
        assertTrue(cut2.getRootDirs().contains(driveE));
        cut1.getRootDirs().remove(driveE);
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(cut1.getRootDirs().contains(driveE));
        assertFalse(cut2.getRootDirs().contains(driveE));
    }
}