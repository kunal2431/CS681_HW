package umbcs681.stream.fs;

import java.util.LinkedList;
import java.util.Objects;

public class FileSystem{

    protected LinkedList<Directory> directories_root = new LinkedList<Directory>();

    private static FileSystem instance = null;

    private FileSystem(){
        }

    public static FileSystem getFileSystem(){
        try{
            return Objects.requireNonNull(instance);
        }
        catch(NullPointerException ex){
            instance = new FileSystem();
            return instance;
        }
    }

    public LinkedList<Directory> getRootDirs(){return this.directories_root;}

    public void appendRootDir(Directory root){
        this.directories_root.add(root);
    }
}