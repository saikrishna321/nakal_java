package com.nakal.screen;

import com.nakal.ScreenExecutor.NakalExecutor;
import org.im4java.core.IM4JavaException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by saikrisv on 02/03/16.
 */
public class WebDriverTest {

    public WebDriver driver;
    NakalExecutor nakalExecutor = new NakalExecutor();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver/chromedriver");
        driver = new ChromeDriver();
        Dimension d = new Dimension(1152,828);
        driver.manage().window().setSize(d);
        driver.get("http://www.google.com");
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
