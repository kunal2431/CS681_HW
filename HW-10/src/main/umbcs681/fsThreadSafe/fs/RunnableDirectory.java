package umbcs681.fsThreadSafe.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableDirectory  extends FSElement implements Runnable{

    protected LinkedList<FSElement> children = new LinkedList<FSElement>();
    protected LinkedList<RunnableDirectory> directories = new LinkedList<RunnableDirectory>();
    protected LinkedList<RunnableFile> files = new LinkedList<RunnableFile>();

    protected LinkedList<RunnableLink> links = new LinkedList<RunnableLink>();

    private AtomicBoolean done = new AtomicBoolean(false);

    public RunnableDirectory(RunnableDirectory parent, String name, int size, LocalDateTime creationTime){
        super(parent, name, 0, creationTime);
    }

    public LinkedList<FSElement> getChildren(){
        rwLock.readLock().lock();
        try{
            return this.children;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void appendChild(FSElement child){
        rwLock.writeLock().lock();
        try{
            this.children.add(child);
            child.setParent(this);

        }
        finally {
            rwLock.writeLock().unlock();
        }

    }

    public int countChildren(){
        rwLock.readLock().lock();
        try{
            return this.children.size();
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public LinkedList<RunnableDirectory> getSubDirectories(){
        directories.clear();
        for(FSElement child: children ){
            rwLock.readLock().lock();
            try{
                if(child.isDirectory() == true){
                    this.directories.add((RunnableDirectory) child);
                    this.directories.addAll(((RunnableDirectory) child).getSubDirectories());
                }
            }
            finally {
                rwLock.readLock().unlock();
            }
        }
        return this.directories;
    }

    public LinkedList<RunnableFile> getFiles(){
        files.clear();
        for(FSElement child: children ){
            rwLock.readLock().lock();
            try{
                if(child.isDirectory() == false && child.isLink() == false){
                    this.files.add((RunnableFile) child);
                }
            }
            finally {
                rwLock.readLock().unlock();
            }

        }
        return this.files;
    }

    public int getTotalSize(){
        int size=0;
        for (FSElement child : children) {
            rwLock.readLock().lock();
            try{
                if (child.isDirectory() == true) {
                    size = size + ((RunnableDirectory) child).getTotalSize();
                } else {
                    size = size + child.getSize();
                }
            }
            finally {
                rwLock.readLock().unlock();
            }

        }
        return size;
    }

    public boolean isDirectory() {
        return true;
    }

    public boolean isLink() {
        return false;
    }

    public LinkedList<RunnableLink> getLink(){
        links.clear();
        for(FSElement child: children){
            rwLock.readLock().lock();
            try{
                if(child.isLink() == true){
                    this.links.add((RunnableLink) child);
                }
            }
            finally {
                rwLock.readLock().unlock();
            }
        }
        return this.links;
    }

    public void accept(FSVisitor v) {
        v.visit(this);
        for (FSElement e: children){
            e.accept(v);
        }
    }

    public void setDone(){done.set(true);}

    public void run() {
        while(true){
            if(done.get()){
                System.out.println("Stopped RunnableDirectory");
                break;
            }
            LocalDateTime currentTime = LocalDateTime.now();
            FileSystem fs = FileSystem.getFileSystem().get();
            RunnableDirectory repo = new RunnableDirectory(null, "repo", 0, currentTime);
            fs.appendRootDir(repo);
            RunnableDirectory src = new RunnableDirectory(repo, "src", 0, currentTime);
            repo.appendChild(src);
            fs.getRootDirs().getFirst().getSubDirectories();
            try{
                Thread.sleep(2);
            }
            catch (InterruptedException ex){
                continue;
            }
        }
    }
}