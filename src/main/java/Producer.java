import java.util.List;

public class Producer  implements Runnable{
    private List<Item> itemList;
    private Integer start;

    public Producer(List<Item> itemList, Integer start) {
        this.itemList = itemList;
        this.start = start;
    }

    @Override
    public void run() {
        for (int i=start; i<itemList.size(); i+=4)
            itemList.get(i).produceMe();
    }
}
