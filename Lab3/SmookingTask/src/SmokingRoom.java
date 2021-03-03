public class SmokingRoom {
    private Ingredient missingIng;

    public Ingredient getMissingIng() {
        return missingIng;
    }

    public void setMissingIng(Ingredient missingIng) {
        this.missingIng = missingIng;
    }

    public void start() throws InterruptedException {
        Semaphore mediatorSemaphore = new Semaphore(1);
        Semaphore smokerSemaphore = new Semaphore(1);
        smokerSemaphore.acquire();
        (new Mediator(mediatorSemaphore,smokerSemaphore,this)).start();
        (new Smoker(Ingredient.Tobacco,mediatorSemaphore,smokerSemaphore,this)).start();
        (new Smoker(Ingredient.Paper, mediatorSemaphore,smokerSemaphore,this)).start();
        (new Smoker(Ingredient.Matches, mediatorSemaphore,smokerSemaphore,this)).start();
    }
}
