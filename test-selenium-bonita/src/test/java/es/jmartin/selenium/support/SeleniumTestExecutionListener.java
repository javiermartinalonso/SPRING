package es.jmartin.selenium.support;

import static es.jmartin.selenium.support.CaseFormat.toLowerUnderscore;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class SeleniumTestExecutionListener extends AbstractTestExecutionListener {

    private WebDriver webDriver;

    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        if (webDriver != null) {
            return;
        }
        ApplicationContext context = testContext.getApplicationContext();
        if (context instanceof ConfigurableApplicationContext) {

            SeleniumTest annotation = findAnnotation(
                    testContext.getTestClass(), SeleniumTest.class);
            webDriver = BeanUtils.instantiate(annotation.driver());

            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
            ConfigurableListableBeanFactory bf = configurableApplicationContext.getBeanFactory();
            bf.registerResolvableDependency(WebDriver.class, webDriver);
        }
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        if (webDriver != null) {
            SeleniumTest annotation = findAnnotation(
                    testContext.getTestClass(), SeleniumTest.class);
            webDriver.get(annotation.baseUrl());
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
//        if (webDriver != null) {
//            webDriver.quit();
//        }
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        if (testContext.getTestException() == null) {
            return;
        }
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        String testName = toLowerUnderscore(testContext.getTestClass().getSimpleName());
        String methodName = toLowerUnderscore(testContext.getTestMethod().getName());
        
        File destFile = new File("C:\\test\\screenshots\\" + testName);
        Path destPath = destFile.toPath();
        
        
        //Files.walkFileTree(screenshot.toPath(), new CopyDirVisitor(screenshot.toPath(), Paths.get(destPath.toString(), "\\" + screenshot.getName()), StandardCopyOption.REPLACE_EXISTING));

        		
        		//screenshot.toPath(), Paths.get(destPath.toString(), "\\" + screenshot.getName() ));
        Files.copy(screenshot.toPath(), Paths.get(destPath.toString(), "\\" + methodName + "_" + screenshot.getName() ));
    }
    
/*    
    public static class CopyDirVisitor extends SimpleFileVisitor<Path>
    {
        private final Path fromPath;
        private final Path toPath;
        private final CopyOption copyOption;

        public CopyDirVisitor(Path fromPath, Path toPath, CopyOption copyOption)
        {
            this.fromPath = fromPath;
            this.toPath = toPath;
            this.copyOption = copyOption;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
            Path targetPath = toPath.resolve(fromPath.relativize(dir));
            if( !Files.exists(targetPath) )
            {
                Files.createDirectory(targetPath);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
            Files.copy(file, toPath.resolve(fromPath.relativize(file)), copyOption);
            return FileVisitResult.CONTINUE;
        }
    }
*/
}
