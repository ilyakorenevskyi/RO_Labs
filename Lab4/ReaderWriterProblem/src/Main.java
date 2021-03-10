public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteManager readWriteManager = new ReadWriteManager("data.txt");
        readWriteManager.addNewRecord("Анасьева Анастасия Анастасьева - 111111");
        readWriteManager.addNewRecord("Анасьева Дарья Анастасьева - 222222");
        readWriteManager.findByName("Иванов Иван Иванович");
        readWriteManager.findByName("Анасьева Дарья Анастасьева");
        readWriteManager.deleteRecord("Анасьева Дарья Анастасьева - 222222");
        readWriteManager.addNewRecord("Анасьева Дарья Анастасьева - 222222");
        readWriteManager.findByName("Анасьева Дарья Анастасьева");
        readWriteManager.findByName("Денисов Денис Денисович");
        Thread.sleep(10000);
        return;
    }
}
