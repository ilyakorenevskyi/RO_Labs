public class CustomThread extends Thread{
    private int value;
    private CustomSlider slider;
    private boolean interrupted = false;
    CustomThread(int value, CustomSlider slider){
        this.value  = value;
        this.slider = slider;
    }
    @Override
    public void run(){
        while(!interrupted) {
            synchronized (slider) {
                slider.addValue(value);
            }
        }
        interrupted = false;
        System.out.println("Ended");
    }
    public void setInterrupted(){
        interrupted = true;
    }
}
