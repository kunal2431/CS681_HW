package umbcs681.ConcurrentQueue.util;
import umbcs681.ConcurrentQueue.fs.*;

import java.util.concurrent.ConcurrentLinkedQueue;

public class FileSearchVisitor implements FSVisitor{

    private String fileName;

    protected ConcurrentLinkedQueue<File> foundFiles = new ConcurrentLinkedQueue<File>();

    public FileSearchVisitor(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void visit(Link link) {
        return;
    }

    @Override
    public void visit(Directory dir) {
        return;
    }

    @Override
    public void visit(File file) {
        if(file.getName().equals(fileName)){
            foundFiles.add(file); }
    }

    public ConcurrentLinkedQueue<File> getFoundFiles(){
        return foundFiles;
    }
}