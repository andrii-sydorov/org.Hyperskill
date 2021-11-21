package File;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * All real applications generate log files for keeping records on their
 * performance, user activities, or other information. To prevent running out of
 * free disk space, sometimes you need to clean up by deleting old files. That
 * is what the code below is supposed to do.
 * 
 * Take a look at the code. The helper method getChildren finds all the files.
 * It takes a directory as a passed argument and returns a list of nested files
 * and directories. If the directory happens to be empty, it returns an empty
 * list.
 * 
 * A file is considered old if it was last modified earlier than the passed
 * thresholdDate.
 * 
 * Your task is to complete the code so it will work properly. What method
 * should be invoked to make the code read only old files and ignore others
 * within directories?
 * 
 * The system of nested directories itself should not be changed.
 * 
 * Fill in the gap in the 7th line.
 */

public class DeleteFiles {
    public void deleteOldFiles(File rootDir, long thresholdDate) {
        Deque<File> files = new ArrayDeque<>(getChildren(rootDir));

        while (files.size() != 0) {
            File file = files.pop();

            if (file.isFile()) {
                if (file.lastModified() < thresholdDate) {
                    file.delete();
                }
            } else {
                files.addAll(getChildren(file));
            }
        }
    }

    private List<File> getChildren(File dir) {
        File[] children = dir.listFiles();

        return children == null || children.length == 0 ? Collections.emptyList() : Arrays.asList(children);
    }
}
