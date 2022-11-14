package owlhome.AOP_practice.object_for_advice;


import owlhome.AOP_practice.poincuts.SayName;

import java.util.stream.Stream;


abstract public class Bird implements Flying {
    private String name;

    public Bird(String name) {
        this.name = name;
    }

    @Override
    public void sayHooray(int number){
        for(int i = 0; i < number; i++){
            System.out.printf("%s: Hooray!\n", getName());
        }
    }

    @Override
    public void fly() {
        System.out.printf("Bird named %s get ready:\n", getName());
        Stream.of("3", "2", "1").forEach(
                n -> {
                    try {
                        System.out.print(n + "...");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        System.out.print("Start!!!\n");
    }

    @SayName
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
