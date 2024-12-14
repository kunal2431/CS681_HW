package umbcs681.fscrawl;

import umbcs681.fscrawl.fs.*;
import umbcs681.fscrawl.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
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
    //Test Case 4: Functional Test: Verify files() stream implementation and groupingby()
    public void verify_StreamGroup(){
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        repo.accept(visitor);
        Map<String, List<File>> grpFiles = visitor.files().collect(groupingBy( (File file)-> { if(file.getName().endsWith(".java")) { return ".java";} else{return ".md";}}));
        LinkedList<Double> avg = new LinkedList<>();
        LinkedList<Long> ct = new LinkedList<>();
        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();
        LinkedList<Long> sum = new LinkedList<>();
        grpFiles.forEach((group, files)->{
            IntSummaryStatistics stats = files.stream().collect(summarizingInt((File file)-> file.getSize()));
            avg.add(stats.getAverage());
            ct.add(stats.getCount());
            min.add(stats.getMin());
            max.add(stats.getMax());
            sum.add(stats.getSum());
        });
        assertEquals(10.00,avg.get(0)); // .md files
        assertEquals(15.00,avg.get(1)); // .java files
        assertEquals(1,ct.get(0)); // .md files
        assertEquals(4,ct.get(1)); // .java files
        assertEquals(10,min.get(0)); // .md files
        assertEquals(12,min.get(1)); // .java files
        assertEquals(10,max.get(0)); // .md files
        assertEquals(20,max.get(1)); // .java files
        assertEquals(10,sum.get(0)); // .md files
        assertEquals(60,sum.get(1)); // .java files
    }

    @Test
    //Test Case 5: Functional Test: Verify FileSearchVisitor implementation
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

    @Test
    //Test Case 8: Functional Test
    public void verifyRunnableFileCrawl_MultipleThreads2(){
        FileSystem cut = FileSystem.getFileSystem().get();
        Directory driveE = new Directory(null, "driveE", 0, currentTime);
        cut.appendRootDir(driveE);
        Directory Movies = new Directory(driveE, "Movies", 0, currentTime);
        driveE.appendChild(Movies);
        Directory Songs = new Directory(driveE, "Songs", 0, currentTime);
        driveE.appendChild(Songs);
        File spiderman = new File(Movies, "spiderman.mp4", 10, currentTime);
        Movies.appendChild(spiderman);

        Directory driveD = new Directory(null, "driveD", 0, currentTime);
        cut.appendRootDir(driveD);
        Directory Documents = new Directory(driveD, "Documents", 0, currentTime);
        driveD.appendChild(Documents);
        Directory photos = new Directory(driveD, "photos", 0, currentTime);
        driveD.appendChild(photos);
        File selfie = new File(Movies, "selfie.jpg", 10, currentTime);
        photos.appendChild(selfie);
        File passport = new File(Movies, "passport.pdf", 10, currentTime);
        Documents.appendChild(passport);

        FileCrawlingVisitor v1 = new FileCrawlingVisitor();
        FileCrawlingVisitor v2 = new FileCrawlingVisitor();
        FileCrawlingVisitor v3 = new FileCrawlingVisitor();
        RunnableFileCrawl r1 = new RunnableFileCrawl(repo, v1);
        RunnableFileCrawl r2 = new RunnableFileCrawl(driveE, v2);
        RunnableFileCrawl r3 = new RunnableFileCrawl(driveD, v3);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t2.start();
        t3.start();
        try{
            Thread.sleep(100); // Sleep(try-catch) is not required here, but used here to validate intermediate results
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        r1.setDone();
        t1.interrupt();
        r2.setDone();
        t2.interrupt();
        r3.setDone();
        t3.interrupt();
        LinkedList<File> intermediate_result = RunnableFileCrawl.getShared_list();
/*        Alternate solution testcase:
        LinkedList<File> intermediate_result1 = r1.getShared_list1();
        intermediate_result1.addAll(r2.getShared_list1());
        intermediate_result1.addAll(r3.getShared_list1());*/
    }

}