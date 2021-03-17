interface MyReadWriteLock {

    MyReentrantReadWriteLock.WriteLock writeLock();
    MyReentrantReadWriteLock.ReadLock  readLock();

}

class MyReentrantReadWriteLock implements MyReadWriteLock{

    private int readLockCount;
    private int writeLockCount;
    private final MyReentrantReadWriteLock.ReadLock readerLock;
    private final MyReentrantReadWriteLock.WriteLock writerLock;

    public MyReentrantReadWriteLock.WriteLock writeLock() {
        return writerLock;
    }
    public MyReentrantReadWriteLock.ReadLock  readLock()  {
        return readerLock;
    }
    public MyReentrantReadWriteLock() {
        readerLock = new ReadLock();
        writerLock = new WriteLock();
    }

    public class ReadLock{

        public synchronized void lock() {
            if(writeLockCount==0) {
                readLockCount++;
            }
            else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void unlock() {
            readLockCount--;
            if(readLockCount==0)
                notify();
        }

    }

    public class WriteLock{

        public synchronized void lock() {
            if(writeLockCount==0 &&
                    readLockCount==0){
                writeLockCount++;
            }
            else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void unlock() {
            writeLockCount--;
            notify();
        }

    }





}