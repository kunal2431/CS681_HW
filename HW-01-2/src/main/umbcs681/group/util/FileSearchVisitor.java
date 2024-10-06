package umbcs681.group.util;
import umbcs681.group.fs.*;

import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor{

    private String fileName;

    protected LinkedList<File> foundFiles = new LinkedList<File>();

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

    public LinkedList<File> getFoundFiles(){
        return foundFiles;
    }
}