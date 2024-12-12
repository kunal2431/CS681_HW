package umbcs681.fsThreadSafe;
import umbcs681.fsThreadSafe.fs.*;

import java.time.LocalDateTime;

public class TestFixtureInitializer{

    protected static LocalDateTime currentTime = LocalDateTime.now();

    public static FileSystem createFS(){
        FileSystem cut = FileSystem.getFileSystem().get();
        RunnableDirectory repo = new RunnableDirectory(null, "repo", 0, currentTime);
        cut.appendRootDir(repo);
        RunnableDirectory src = new RunnableDirectory(repo, "src", 0, currentTime);
        repo.appendChild(src);
        RunnableDirectory main = new RunnableDirectory(src, "main", 0, currentTime);
        src.appendChild(main);
        RunnableDirectory test = new RunnableDirectory(src, "test", 0, currentTime);
        src.appendChild(test);
        RunnableFile readme = new RunnableFile(repo, "readme.md", 10, currentTime);
        repo.appendChild(readme);
        RunnableFile A = new RunnableFile(main, "A.java", 20, LocalDateTime.of(2023, 1, 1, 0, 0));
        main.appendChild(A);
        RunnableFile B = new RunnableFile(main, "B.java", 15, currentTime);
        main.appendChild(B);
        RunnableFile ATest = new RunnableFile(test, "ATest.java", 13, currentTime);
        test.appendChild(ATest);
        RunnableFile BTest = new RunnableFile(test, "BTest.java", 12, currentTime);
        test.appendChild(BTest);
        RunnableLink rm = new RunnableLink(test, "rm.md", 0, currentTime, readme);
        test.appendChild(rm);

        return cut;
    }

}