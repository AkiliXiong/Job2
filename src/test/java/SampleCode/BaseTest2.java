package SampleCode;

import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.exec.CommandLine;

import java.io.IOException;
import java.net.URL;

public abstract class BaseTest2 {
    @BeforeSuite
    public void before(){
        CommandLine cmd = new CommandLine("/usr/local/Cellar/node/10.10.0/bin/node");
        //CommandLine cmd = new CommandLine("/Applications/Appium1.8.1.app/Contents/Resources/app/node_modules/babel-core/node_modules/babel-generator/lib/node");
        cmd.addArgument("/Applications/Appium1.8.1.app/Contents/Resources/app/node_modules/appium/build/lib/appium.js");
        cmd.addArgument("--address");
        cmd.addArgument("127.0.0.1");
        cmd.addArgument("--port");
        cmd.addArgument("4723");

        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try
        {
            executor.execute(cmd,handler);
            Thread.sleep(10000);
            System.out.println("appium setup");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @AfterSuite
    public void after()
    {
        Runtime runtime = Runtime.getRuntime();
        try
        {
            runtime.exec("ps -ef | grep 'node' | grep -v grep | awk '{print $2}' | xargs kill -9");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
