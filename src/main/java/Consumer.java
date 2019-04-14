import java.util.List;

public class Consumer  implements Runnable{
    private List<Item> itemList;
    private Integer start;
    private Integer numberOfConsumer;

    public Consumer(List<Item> itemList, Integer start, Integer numberOfConsumer) {
        this.itemList = itemList;
        this.start = start;
        this.numberOfConsumer = numberOfConsumer;
    }

    @Override
    public void run() {
        for (int i=start; i<itemList.size(); i+=numberOfConsumer)
            itemList.get(i).consumeMe();
    }
}
