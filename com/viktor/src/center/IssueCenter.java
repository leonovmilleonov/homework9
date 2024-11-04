package com.viktor.src.center;

import com.viktor.src.dto.DataRow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.*;

public class IssueCenter extends Thread {
    private static final int INITIAL_CAPACITY = 50;
    private static final int TIME_TO_SLEEP = 120;
    private final Path path;
    private final ExecutorService threadPool;
    private BlockingQueue<DataRow> complains = new ArrayBlockingQueue<>(INITIAL_CAPACITY);


    public IssueCenter(Path path) {
        this.path = path;
        threadPool = Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        while (true) {
            addTasks();
            threadPool.execute(new Operator(complains));
            threadPool.execute(new Operator(complains));
            threadPool.execute(new Operator(complains));
            try {
                Thread.sleep(TIME_TO_SLEEP * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addTasks() {
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                String[] strings = scanner.nextLine().split(",");
                complains.add(new DataRow(
                        Integer.parseInt(strings[0]),
                        strings[1],
                        strings[2],
                        strings[3],
                        strings[4]));
            }
            scanner.close();
            Files.writeString(path, "");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getPath() {
        return path;
    }
}
