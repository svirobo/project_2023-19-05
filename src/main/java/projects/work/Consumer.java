package projects.work;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    final private BlockingQueue<Message> queue;
    final private Database database;

    public Consumer(BlockingQueue<Message> queue, Database database) {
        this.queue = queue;
        this.database = database;
    }

    @Override
    public void run() {
        try {
            while (queue.size() > 0) {
                final Message message = queue.take();
                Identifiable item = message.item();
                switch (message.operation()) {
                    case ADD -> database.putItem(message.type(), item);
                    case PRINT_ALL -> database.printIndexValues(message.type());
                    case DELETE_ALL -> database.deleteIndex(message.type());
                }
            }
            System.out.println((Thread.currentThread().getName() + " is done."));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted!");
        }
    }
}
