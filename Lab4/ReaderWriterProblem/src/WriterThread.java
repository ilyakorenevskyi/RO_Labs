import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriterThread extends Thread {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String file;
    private String toWrite;

    public WriterThread(ReentrantReadWriteLock lock, String file,  String toWrite ) {
        this.lock = lock;
        this.file = file;
        this.toWrite = toWrite;
    }

    @Override
    public void run() {
        try {
            lock.writeLock().lock();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(toWrite).append("\n");
            writer.close();
            lock.writeLock().unlock();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
