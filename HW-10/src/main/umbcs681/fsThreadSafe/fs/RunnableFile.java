package umbcs681.fsThreadSafe.fs;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableFile extends FSElement implements Runnable{

    public RunnableFile(RunnableDirectory parent, String name, int size, LocalDateTime creationTime){
        super(parent, name, size, creationTime);
    }
    private AtomicBoolean done = new AtomicBoolean(false);

    public boolean isDirectory() {
        return false;
    }

    public boolean isLink() {
        return false;
    }

    public void accept(FSVisitor v) {
        v.visit(this);
    }

    public void setDone(){done.set(true);}

    public void run() {
        while(true){
            if(done.get()){
                System.out.println("Stopped RunnableFile");
                break;
            }
            LocalDateTime currentTime = LocalDateTime.now();
            FileSystem fs = FileSystem.getFileSystem().get();
            RunnableDirectory repo = new RunnableDirectory(null, "repo", 0, currentTime);
            fs.appendRootDir(repo);
            RunnableFile readme = new RunnableFile(repo, "readme.md", 10, currentTime);
            repo.appendChild(readme);
            fs.getRootDirs().getFirst().getFiles();
            try{
                Thread.sleep(2);
            }
            catch (InterruptedException ex){
                continue;
            }
        }
    }
}