package com.nakal.ScreenExecutor;

import org.im4java.core.IM4JavaException;
import java.io.IOException;
import static com.nakal.ScreenExecutor.NakalAttributeValidator.isBuildMode;

/**
 * Created by saikrisv on 22/02/16.
 */
public class NakalExecutor {

    private ExecutorService executorService;

    public NakalExecutor(){
        executorService= new ExecutorService();
    }

    /**
     * @param baseLineImageName
     * @return false if actual and expected images are not similar and generate a difference Image
     */
    public boolean nakalExecutorNativeCompare(String baseLineImageName)
        throws InterruptedException, IOException, IM4JavaException {

        return isBuildMode() ? executorService.buildMode(baseLineImageName)
                : executorService.compareMode(baseLineImageName,0);
    }
    /**
     * @param baseLineImageName
     * @param threshold
     * @return false if actual and expected images are not similar and generate a difference Image
     */
    public boolean nakalExecutorNativeCompare(String baseLineImageName, int threshold)
            throws InterruptedException, IOException, IM4JavaException {

        return isBuildMode() ? executorService.buildMode(baseLineImageName)
                : executorService.compareMode(baseLineImageName,threshold);
    }

}
