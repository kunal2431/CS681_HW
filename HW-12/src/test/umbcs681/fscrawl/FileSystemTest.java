package umbcs681.fscrawl;
import umbcs681.fscrawl.fs.*;

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
        FileSystem cut1 = FileSystem.getFileSystem().get();
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
        RunnableFileSystem r1 = new RunnableFileSystem();
        RunnableFileSystem r2 = new RunnableFileSystem();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    @Test
    //Test Case 6: Functional Test
    public void verifyFileSystem_SingletonMultipleThreads1(){
        RunnableFileSystem r1 = new RunnableFileSystem();
        RunnableFileSystem r2 = new RunnableFileSystem();
        RunnableFileSystem r3 = new RunnableFileSystem();
        RunnableFileSystem r4 = new RunnableFileSystem();
        RunnableFileSystem r5 = new RunnableFileSystem();
        RunnableFileSystem r6 = new RunnableFileSystem();
        RunnableFileSystem r7 = new RunnableFileSystem();
        RunnableFileSystem r8 = new RunnableFileSystem();
        RunnableFileSystem r9 = new RunnableFileSystem();
        RunnableFileSystem r10 = new RunnableFileSystem();
        RunnableFileSystem r11 = new RunnableFileSystem();
        RunnableFileSystem r12 = new RunnableFileSystem();
        RunnableFileSystem r13 = new RunnableFileSystem();
        RunnableFileSystem r14 = new RunnableFileSystem();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        Thread t5 = new Thread(r5);
        Thread t6 = new Thread(r6);
        Thread t7 = new Thread(r7);
        Thread t8 = new Thread(r8);
        Thread t9 = new Thread(r9);
        Thread t10 = new Thread(r10);
        Thread t11 = new Thread(r11);
        Thread t12 = new Thread(r12);
        Thread t13 = new Thread(r13);
        Thread t14 = new Thread(r14);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        r1.setDone();
        t1.interrupt();
        r2.setDone();
        t2.interrupt();
        r3.setDone();
        t3.interrupt();
        r4.setDone();
        t4.interrupt();
        r5.setDone();
        t5.interrupt();
        r6.setDone();
        t6.interrupt();
        r7.setDone();
        t7.interrupt();
        r8.setDone();
        t8.interrupt();
        r9.setDone();
        t9.interrupt();
        r10.setDone();
        t10.interrupt();
        r11.setDone();
        t11.interrupt();
        r12.setDone();
        t12.interrupt();
        r13.setDone();
        t13.interrupt();
        r14.setDone();
        t14.interrupt();
    }

}