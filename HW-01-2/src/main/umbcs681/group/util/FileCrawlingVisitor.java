package umbcs681.group.util;
import umbcs681.group.fs.*;

import java.util.LinkedList;
import java.util.stream.Stream;
import java.time.LocalDateTime;

public class FileCrawlingVisitor implements FSVisitor{

    protected LinkedList<File> files_visitor = new LinkedList<File>();

    public FileCrawlingVisitor(){}

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
        files_visitor.add(file);
    }

    public LinkedList<File> getFiles(){
        return files_visitor;
    }

    public Stream<File> files(){
        return getFiles().stream();
    }
}
