package projects.work;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private final static Database DATABASE = new Database();
    private final static BlockingQueue<Message> QUEUE = new LinkedBlockingQueue<>(20);

    static {
        // fill our queue with some messages for processing
        QUEUE.offer(new Message(new User(1, "uuid1", "testUser1"), Message.Operation.ADD, User.class));
        QUEUE.offer(new Message(new User(2, "uuid2", "testUser2"), Message.Operation.ADD, User.class));
        QUEUE.offer(new Message(null, Message.Operation.PRINT_ALL, User.class));
        QUEUE.offer(new Message(null, Message.Operation.DELETE_ALL, User.class));
        QUEUE.offer(new Message(null, Message.Operation.PRINT_ALL, User.class));
    }

    public static void main(String[] args) {
        final List<Thread> consumers = createConsumers();
        consumers.forEach(Thread::start);
    }

    public static List<Thread> createConsumers() {
        final List<Thread> threadList = new ArrayList<>();
        final Consumer consumer1 = new Consumer(QUEUE, DATABASE);
        threadList.add(new Thread(consumer1));

        // program will be non-deterministic if we run it in multiple threads
//        final Consumer consumer2 = new Consumer(QUEUE, DATABASE);
//        threadList.add(new Thread(consumer2));
//
//        final Consumer consumer3 = new Consumer(QUEUE, DATABASE);
//        threadList.add(new Thread(consumer3));

        return threadList;
    }
}