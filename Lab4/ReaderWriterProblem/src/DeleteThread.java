import java.io.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DeleteThread extends Thread {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String file;
    private String toDelete;

    public DeleteThread(ReentrantReadWriteLock lock, String file , String toDelete ) {
        this.lock = lock;
        this.file = file;
        this.toDelete = toDelete;
    }

    @Override
    public void run() {
        try {
            lock.writeLock().lock();
            File inputFile = new File(file);
            File tempFile = new File("tempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(toDelete)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            boolean deletion = inputFile.delete();
            boolean successful = tempFile.renameTo(inputFile);
            lock.writeLock().unlock();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}