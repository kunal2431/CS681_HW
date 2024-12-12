package umbcs681.fsThreadSafe;
import umbcs681.fsThreadSafe.fs.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class LinkTest{

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
    private static RunnableLink rm;
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

    private String[] linkToStringArray(RunnableLink l){
        String[] linkInfo = { l.getName(), String.valueOf(l.getSize()), String.valueOf(l.getcreationTime())};
        return linkInfo;
    }

    @Test
    //Test Case 1: Functional Test: Verify Equality Check in Link
    public void verifyEqualityCheckLink(){
        String[] expected = {"rm.md", "0", String.valueOf(currentTime)};
        assertTrue(rm instanceof RunnableLink);
        assertArrayEquals(expected, linkToStringArray(rm));
        assertFalse(rm.isDirectory());
        assertTrue(rm.isLink());
    }

    @Test
    //Test Case 2: Functional Test: verify Links in a Directory
    public void verifyLinkInDirectory(){
        LinkedList<RunnableLink> actual = test.getLink();
        assertTrue(actual.getFirst() instanceof RunnableLink);
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