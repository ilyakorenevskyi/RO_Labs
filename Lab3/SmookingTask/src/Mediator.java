import java.util.Random;
public class Mediator extends Thread{
    private Semaphore mediatorSemaphore;
    private Semaphore smokerSemaphore;
    private SmokingRoom smokingRoom;
    private Random random = new Random();
    public Mediator( Semaphore mediatorSemaphore, Semaphore smokerSemaphore, SmokingRoom smokingRoom){
        this.smokingRoom = smokingRoom;
        this.smokerSemaphore = smokerSemaphore;
        this.mediatorSemaphore = mediatorSemaphore;
    }
    @Override
    public void run() {
        while(true){
            try {
                mediatorSemaphore.acquire();
                System.out.println("Mediator is choosing ingredients");
                Thread.sleep(2000);
                int missingIng = random.nextInt(3);
                switch (missingIng){
                    case (0) : {
                        System.out.println(Ingredient.Tobacco.toString() + " is missing");
                        smokingRoom.setMissingIng(Ingredient.Tobacco);
                        break;
                    }
                    case (1) : {
                        System.out.println(Ingredient.Paper.toString() +  " is missing");
                        smokingRoom.setMissingIng(Ingredient.Paper);
                        break;
                    }
                    case (2) : {
                        System.out.println(Ingredient.Matches.toString() + " is missing");
                        smokingRoom.setMissingIng(Ingredient.Matches);
                        break;
                    }
                }

                smokerSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
