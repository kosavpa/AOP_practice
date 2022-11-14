package owlhome.AOP_practice.object_for_advice;


public class Owl extends Bird{
    public Owl(String birdName) {
        super(birdName);
    }

    @Override
    public void fly() {
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.printf("Comrade %s: %d seconds, normal flight!\n", this.getName(), i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Comrade %s: see Ia, throw off your tail and going to land!\n", this.getName());
    }

    @Override
    public void sayQuote(){
        System.out.println("Free of charge - that is, for free!");
    }
}
