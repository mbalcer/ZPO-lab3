import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        long startTime = 0, endTime = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Pojedyńcze wątki");
        System.out.println("2. Pula wątków");
        System.out.println("3. Stream API");
        System.out.print("Wybór: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                List<Thread> threadList = createdListThread(itemList, NUMBER_OF_PRODUCER, NUMBER_OF_CONSUMER);
                startTime = System.nanoTime();
                for (Thread thread : threadList){
                    thread.start();
                }
                for (Thread thread : threadList){
                    thread.join();
                }
                endTime = System.nanoTime();
            break;
            case 2:
                startTime = System.nanoTime();
                ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_PRODUCER+NUMBER_OF_CONSUMER);
                itemList.stream()
                        .forEach(item -> {
                            executor.submit(new Task(item));
                        });
                executor.shutdown();
                while (!executor.isTerminated()) { }
                endTime = System.nanoTime();
            break;
            case 3:
                startTime = System.nanoTime();
                itemList.stream()
                        .forEach(item -> {
                            item.produceMe();
                            item.consumeMe();
                        });
                endTime = System.nanoTime();
            break;
        }
        System.out.println("Czas: " + ((endTime - startTime) / 1000000000) + "s" );
    }

}
