package com.nakal.imageutil;

import org.im4java.core.*;
import org.im4java.process.ArrayListErrorConsumer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by saikrisv on 22/02/16.
 */
public class ImageUtil {
    ArrayListErrorConsumer arrayListErrorConsumer = new ArrayListErrorConsumer();

    /**
     * @param actualImage
     * @param expectedImage
     * @param diffImage
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws IM4JavaException
     */
    public boolean compareImages(String actualImage,
                                 String expectedImage, String diffImage) throws IOException,
            InterruptedException, IM4JavaException {
        IMOps cmpOp = new IMOperation();
        cmpOp.metric("AE");
        cmpOp.fuzz(10.00);
        cmpOp.addImage();
        cmpOp.addImage();
        cmpOp.addImage();
        CompareCmd compare = new CompareCmd();
        compare.setErrorConsumer(arrayListErrorConsumer);
        try {
            compare.run(cmpOp, actualImage, expectedImage);
            System.out.println("**Image Is Same**");
            return true;
        } catch (Throwable e) {
            try {
                compare.run(cmpOp, actualImage, expectedImage, diffImage);

            } catch (Throwable e1) {
                System.out.println("Total Pixel Difference of the Images:::" + arrayListErrorConsumer.getOutput().get(0));
            }
            return false;
        }

    }


    /**
     * returns true if the images are similar
     * returns false if the images are not identical
     *
     * @param actualImage
     * @param expectedImage
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws IM4JavaException
     */
    public boolean compareImages(String actualImage,
                                 String expectedImage) throws IOException,
            InterruptedException, IM4JavaException {
        IMOps cmpOp = new IMOperation();
        cmpOp.metric("AE");
        cmpOp.fuzz(10.00);
        cmpOp.addImage();
        cmpOp.addImage();
        cmpOp.addImage();
        CompareCmd compare = new CompareCmd();
        compare.setErrorConsumer(arrayListErrorConsumer);
        try {
            compare.run(cmpOp, actualImage, expectedImage);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * @param actualImage actualImagePath
     * @param value       set the ignore % of image pixels
     * @throws IOException
     */
    public boolean compareImages(String expectedImage, String actualImage,String diffImage, int value)
            throws IOException, IM4JavaException, InterruptedException {
        compareImages(actualImage, expectedImage,diffImage);
        long totalImagePixel = getCompleteImagePixel(actualImage);
        if(arrayListErrorConsumer.getOutput().get(0).contains("+") == true){
            return false;
        }else{
            long totalPixelDifferne = Integer.parseInt(arrayListErrorConsumer.getOutput().get(0));

            double c = ((double) totalPixelDifferne / totalImagePixel) * 100;
            long finalPercentageDifference = Math.round(c);
            System.out.println("Difference in the images is ::" + finalPercentageDifference +"%");
            try {
                if (finalPercentageDifference <= value) {
                    return true;
                }
            } catch (NumberFormatException e) {
            }
            return false;
        }
    }

    /**
     * @param actualImage
     * @param maskImage
     * @param maskedImage
     * @throws IOException
     * @throws InterruptedException
     * @throws IM4JavaException
     */
    public void maskImage(String actualImage, String maskImage,
                                 String maskedImage) throws IOException, InterruptedException,
            IM4JavaException {
        IMOperation op = new IMOperation();
        op.addImage(actualImage);
        op.addImage(maskImage);
        op.alpha("on");
        op.compose("DstOut");
        op.composite();
        op.addImage(maskedImage);
        ConvertCmd convert = new ConvertCmd();
        convert.run(op);
    }

    /**
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    public void mergeImagesHorizontally(String expected, String actual, String diffImage, String mergedImage)
            throws InterruptedException, IOException, IM4JavaException {
        ConvertCmd cmd1 = new ConvertCmd();
        IMOperation op1 = new IMOperation();
        op1.addImage(expected); // source file
        op1.addImage(actual); // destination file file
        op1.addImage(diffImage);
        op1.resize(1024,576);
        op1.p_append();
        op1.addImage(mergedImage);
        cmd1.run(op1);
    }


    public int getCompleteImagePixel(String actualImage) throws IOException {
        BufferedImage readImage = null;
        readImage = ImageIO.read(new File(actualImage));
        int h = readImage.getHeight();
        int w = readImage.getWidth();
        return h * w;
    }

}
