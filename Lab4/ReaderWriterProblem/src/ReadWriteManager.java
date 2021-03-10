import javax.swing.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteManager {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String fileName;
    public ReadWriteManager(String fileName){
        this.fileName = fileName;
    }
    public void findByName(String name){
        (new ReaderThread(lock,fileName,true,name)).start();
    }
    public void findByNumber(String number){
        (new ReaderThread(lock,fileName,false,number)).start();
    }
    public void addNewRecord(String record){
        (new WriterThread(lock,fileName,record)).start();
    }
    public void deleteRecord(String record){
        (new DeleteThread(lock,fileName,record)).start();
    }
}
