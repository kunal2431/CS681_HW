package umbcs681.ConcurrentQueue;
import umbcs681.ConcurrentQueue.fs.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class LinkTest{

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
        rm = test.getLink().peek();
    }

    private String[] linkToStringArray(Link l){
        String[] linkInfo = { l.getName(), String.valueOf(l.getSize()), String.valueOf(l.getcreationTime())};
        return linkInfo;
    }

    @Test
    //Test Case 1: Functional Test: Verify Equality Check in Link
    public void verifyEqualityCheckLink(){
        String[] expected = {"rm.md", "0", String.valueOf(currentTime)};
        assertTrue(rm instanceof Link);
        assertArrayEquals(expected, linkToStringArray(rm));
        assertFalse(rm.isDirectory());
        assertTrue(rm.isLink());
    }

    @Test
    //Test Case 2: Functional Test: verify Links in a Directory
    public void verifyLinkInDirectory(){
        LinkedList<Link> actual = new LinkedList<>(test.getLink());
        assertTrue(actual.getFirst() instanceof Link);
        assertEquals(1, actual.size());
        assertTrue(actual.contains(rm));
        assertFalse(actual.contains(ATest));
    }

    @Test
    //Test Case 3: Structural Test: Verify public methods getLinkTarget()
    public void verifyLink_getLinkTarget(){
        FSElement actual = rm.getLinkTarget();
        assertSame(readme, actual);
    }

}