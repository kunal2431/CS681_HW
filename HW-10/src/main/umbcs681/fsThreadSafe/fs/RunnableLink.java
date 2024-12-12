package umbcs681.fsThreadSafe.fs;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableLink extends FSElement implements Runnable{

    private FSElement target;

    public RunnableLink(RunnableDirectory parent, String name, int size, LocalDateTime creationTime, FSElement target){
        super(parent, name, 0, creationTime);
        this.target = target;
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isLink() {
        return true;
    }

    public void accept(FSVisitor v) {
        v.visit(this);
    }

    public FSElement getLinkTarget(){
        rwLock.readLock().lock();
        try{
            return this.target;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    private AtomicBoolean done = new AtomicBoolean(false);

    public void setDone(){done.set(true);}

    public void run() {
        while(true){
            if(done.get()){
                System.out.println("Stopped RunnableLink");
                break;
            }
            LocalDateTime currentTime = LocalDateTime.now();
            FileSystem fs = FileSystem.getFileSystem().get();
            RunnableDirectory repo = new RunnableDirectory(null, "repo", 0, currentTime);
            fs.appendRootDir(repo);
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
            test.getLink();
            try{
                Thread.sleep(2);
            }
            catch (InterruptedException ex){
                continue;
            }
        }
    }
}