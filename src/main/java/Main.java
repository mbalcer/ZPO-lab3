import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static List<Item> generate100Item() {
        return Stream.generate(Item::new)
                .limit(100)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws InterruptedException {
        List<Item> itemList = generate100Item();
        long startTime, endTime;

        List<Thread> threadList = new ArrayList<>(7);
        for (int i=0; i<4; i++) {
            Thread t = new Thread(new Producer(itemList, i));
            threadList.add(t);
        }

        for (int i=0; i<3; i++) {
            Thread t = new Thread(new Consumer(itemList, i));
            threadList.add(t);
        }

        startTime = System.nanoTime();
        for (Thread thread : threadList){
            thread.start();
        }
        for (Thread thread : threadList){
            thread.join();
        }
        endTime = System.nanoTime();

        System.out.println("Czas: " + ((endTime - startTime) / 1000000000) + "s" );
    }

}
