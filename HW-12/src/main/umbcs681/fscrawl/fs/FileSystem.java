package umbcs681.fscrawl.fs;

import java.util.LinkedList;
//import java.util.Objects;

import java.util.concurrent.atomic.*;

public class FileSystem{

    protected LinkedList<Directory> directories_root = new LinkedList<Directory>();

    private static AtomicReference<FileSystem> instance = new AtomicReference<>();

    private FileSystem(){
        }

    public static AtomicReference<FileSystem> getFileSystem(){
        instance.compareAndSet(null, new FileSystem());
        return instance;
/*        lock.lock();
        try{
            if(instance.get()==null){
                instance.set(new FileSystem());
            }
            return instance.get();
        }
        finally {
            lock.unlock();
        }*/
    }

    public LinkedList<Directory> getRootDirs(){return this.directories_root;}

    public void appendRootDir(Directory root){
        this.directories_root.add(root);
    }

}