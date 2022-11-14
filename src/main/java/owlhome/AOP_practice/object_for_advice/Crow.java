package owlhome.AOP_practice.object_for_advice;


public class Crow extends Bird{
    public Crow(String name) {
        super(name);
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

        System.out.printf("Comrade %s: i see cheese at a tree, it's a trap, going to land!\n", this.getName());
    }

    @Override
    public void sayQuote(){
        System.out.println("Kaaaaar!");
    }
}
