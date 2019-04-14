import java.util.List;

public class Producer  implements Runnable{
    private List<Item> itemList;
    private Integer start;
    private Integer numberOfProducer;

    public Producer(List<Item> itemList, Integer start, Integer numberOfProducer) {
        this.itemList = itemList;
        this.start = start;
        this.numberOfProducer = numberOfProducer;
    }

    @Override
    public void run() {
        for (int i=start; i<itemList.size(); i+=numberOfProducer)
            itemList.get(i).produceMe();
    }
}
