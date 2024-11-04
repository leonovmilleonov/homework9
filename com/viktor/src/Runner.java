package com.viktor.src;

import com.viktor.src.center.IssueCenter;
import java.nio.file.Path;

public class Runner {
    public static void main(String[] args) {
        Path path = Path.of("com","viktor", "src", "files", "complains.log");
        IssueCenter issueCenter = new IssueCenter(path);
        issueCenter.start();
    }
}
