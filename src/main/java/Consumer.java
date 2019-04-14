import java.util.List;

public class Consumer  implements Runnable{
    private List<Item> itemList;
    private Integer start;

    public Consumer(List<Item> itemList, Integer start) {
        this.itemList = itemList;
        this.start = start;
    }

    @Override
    public void run() {
        for (int i=start; i<itemList.size(); i+=3)
            itemList.get(i).consumeMe();
    }
}
