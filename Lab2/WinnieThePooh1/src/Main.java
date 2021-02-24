public class Main {
    public static void main(String[] args) throws InterruptedException {
        BeeManager manager = new BeeManager(20);
        manager.findBear();
        Thread.sleep(2000);
        return;
    }

}
