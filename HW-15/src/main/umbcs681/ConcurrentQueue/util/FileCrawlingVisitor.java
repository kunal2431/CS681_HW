package umbcs681.ConcurrentQueue.util;
import umbcs681.ConcurrentQueue.fs.*;

import java.util.stream.Stream;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FileCrawlingVisitor implements FSVisitor{

    protected ConcurrentLinkedQueue<File> files_visitor = new ConcurrentLinkedQueue<File>();

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
        files_visitor.offer(file);
    }

    public ConcurrentLinkedQueue<File> getFiles(){
        return files_visitor;
    }

    public Stream<File> files(){
        return getFiles().stream();
    }
}
