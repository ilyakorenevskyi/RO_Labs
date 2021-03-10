import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderThread extends Thread{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String file;
    private String toFind;
    private boolean mode;

    public ReaderThread(ReentrantReadWriteLock lock, String file, boolean mode , String toFind ) {
        this.lock = lock;
        this.file = file;
        this.mode = mode;
        this.toFind = toFind;
    }

    @Override
    public void run() {
        try {
            lock.readLock().lock();
            Scanner scanner = new Scanner(new File(file));
            String currString;
            while (scanner.hasNext()){
                currString = scanner.nextLine();
               if(mode){
                   if(currString.indexOf('-')>=0){
                       String FIO = currString.substring(0,currString.indexOf('-')-1);
                       if(FIO.contains(toFind)){
                           System.out.println(currString.trim());
                           break;
                       }

                   }
               }
               else{
                   if(currString.indexOf('-')>=0){
                       String number = currString.substring(currString.indexOf('-')+1) ;
                       if(number.contains(toFind)){
                           System.out.println(currString.trim());
                           break;
                       }
                   }
               }
            }
            scanner.close();
            lock.readLock().unlock();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
