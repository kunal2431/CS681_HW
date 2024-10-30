package umbcs681.lockfs.fs;

import java.util.LinkedList;
//import java.util.Objects;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem{

    protected LinkedList<Directory> directories_root = new LinkedList<Directory>();

    private static FileSystem instance = null;
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem(){
        }

    public static FileSystem getFileSystem(){
        lock.lock();
        try{
            if(instance==null){instance = new FileSystem();}
            return instance;
        }
        finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs(){return this.directories_root;}

    public void appendRootDir(Directory root){
        this.directories_root.add(root);
    }

}