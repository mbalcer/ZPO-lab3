public class Task implements Runnable {

    private Item item;

    public Task(Item item) {
        this.item = item;
    }

    @Override
    public void run() {
        item.produceMe();
        item.consumeMe();
    }
}
