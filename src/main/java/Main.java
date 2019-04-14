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

    private static List<Thread> createdListThread(List<Item> itemList, Integer numberOfProducer, Integer numberOfConsumer) {
        List<Thread> threadList = new ArrayList<>(numberOfConsumer + numberOfProducer);
        for (int i=0; i<numberOfProducer; i++) {
            Thread t = new Thread(new Producer(itemList, i, numberOfProducer));
            threadList.add(t);
        }

        for (int i=0; i<numberOfConsumer; i++) {
            Thread t = new Thread(new Consumer(itemList, i, numberOfConsumer));
            threadList.add(t);
        }
        return threadList;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Item> itemList = generate100Item();
        final Integer NUMBER_OF_PRODUCER = 4;
        final Integer NUMBER_OF_CONSUMER = 3;
        long startTime, endTime;

        List<Thread> threadList = createdListThread(itemList, NUMBER_OF_PRODUCER, NUMBER_OF_CONSUMER);

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
