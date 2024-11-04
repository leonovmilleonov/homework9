package com.viktor.src.center;

import com.viktor.src.dto.DataRow;
import com.viktor.src.utils.FileUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;

public class Operator implements Runnable {
    private static final int TIME_TO_WORK = 5;
    private static final Path OUTPUT_PATH = Path.of("com", "viktor", "src", "files", "processed.log");
    private final BlockingQueue<DataRow> complains;

    public Operator(BlockingQueue<DataRow> complains) {
        this.complains = complains;
    }

    @Override
    public void run() {
        while (!complains.isEmpty()) {
            try {
                DataRow currentComplain = complains.take();
                System.out.println(Thread.currentThread().getName() + " оператор" +
                                   " выполняет жалобу " + currentComplain.getId());
                processTheComplain(currentComplain);
                Thread.sleep(TIME_TO_WORK * 1000);
                System.out.println(Thread.currentThread().getName() + "оператор " +
                                   " обработал жалобу " + currentComplain.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processTheComplain(DataRow dataRow) {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_PATH.toFile(), true)) {
            dataRow.setTime(LocalDateTime.now().toString());
            fileWriter.write(FileUtil.formateRow(dataRow) + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
