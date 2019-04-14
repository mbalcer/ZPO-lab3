import java.util.List;

public class Task implements Runnable {

    private List<Item> itemList;
    private Integer start;

    public Task(List<Item> itemList, Integer start) {
        this.itemList = itemList;
        this.start = start;
    }

    @Override
    public void run() {
        for (int i=start; i<itemList.size(); i+=7) {
            itemList.get(i).produceMe();
            itemList.get(i).consumeMe();
        }
    }
}
