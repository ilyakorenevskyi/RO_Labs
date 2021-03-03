public class Smoker extends Thread{
    private Semaphore mediatorSemaphore;
    private Semaphore smokerSemaphore;
    private SmokingRoom smokingRoom;
    private Ingredient ingredient;
    public Smoker(Ingredient ingredient, Semaphore mediatorSemaphore, Semaphore smokerSemaphore, SmokingRoom smokingRoom){
        this.ingredient = ingredient;
        this.smokingRoom = smokingRoom;
        this.smokerSemaphore = smokerSemaphore;
        this.mediatorSemaphore = mediatorSemaphore;
    }
    @Override
    public void start() {
        try {
            smokerSemaphore.acquire();
            if(smokingRoom.getMissingIng() == ingredient){
                System.out.println("Smoker is smoking, please wait");
                Thread.sleep(2000);
                mediatorSemaphore.release();
            }
            else{
                smokerSemaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
