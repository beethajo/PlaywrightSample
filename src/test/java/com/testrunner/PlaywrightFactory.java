package com.testrunner;

import com.microsoft.playwright.*;

import java.io.IOException;

public class PlaywrightFactory {

    public static  final  ThreadLocal<Playwright> playWrightThread = new ThreadLocal<>();
    public static  final  ThreadLocal<BrowserType> browserTypeThread = new ThreadLocal<>();
    public static  final  ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    public static  final  ThreadLocal<BrowserContext> browserContextThread = new ThreadLocal<>();
    public static  final  ThreadLocal<Page> pageThread = new ThreadLocal<>();

    public static synchronized Page getPage() throws IOException {
        if (playWrightThread.get() == null) {
            Playwright playwright = Playwright.create();
            playWrightThread.set(playwright);
//            Page page = createPage(playwright);
     //       pageThread.set(page);
        }
        return  pageThread.get();
    }


}
