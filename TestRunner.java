package com.opencart.tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.TestPlan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;


public class TestRunner {

    public static void main(String[] args) {
        System.out.println("بدء تشغيل اختبارات موقع OpenCart التجريبي...");


        SummaryGeneratingListener listener = new SummaryGeneratingListener();


        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("com.opencart.tests"))
                .build();


        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);


        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();


        Path resultsDir = Paths.get("test-results");
        try {
            if (!Files.exists(resultsDir)) {
                Files.createDirectory(resultsDir);
            }


            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String logFileName = "test-results/test_results_" + timestamp + ".txt";

            try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName))) {
                writer.println("نتائج اختبارات موقع OpenCart التجريبي - " + timestamp);
                writer.println("==================================================");
                writer.println();

                writer.println("ملخص النتائج:");
                writer.println("------------");
                writer.println("عدد الاختبارات التي تم تشغيلها: " + summary.getTestsFoundCount());
                writer.println("عدد الاختبارات الناجحة: " + summary.getTestsSucceededCount());
                writer.println("عدد الاختبارات الفاشلة: " + summary.getTestsFailedCount());
                writer.println("الوقت الإجمالي: " + summary.getTimeFinished() + " مللي ثانية");
                writer.println();


                if (summary.getTestsFailedCount() > 0) {
                    writer.println("تفاصيل الاختبارات الفاشلة:");
                    writer.println("------------------------");
                    List<TestExecutionSummary.Failure> failures = summary.getFailures();
                    for (TestExecutionSummary.Failure failure : failures) {
                        writer.println("اختبار: " + failure.getTestIdentifier().getDisplayName());
                        writer.println("سبب الفشل: " + failure.getException().getMessage());
                        writer.println();
                    }
                }
            }

            System.out.println("تم حفظ نتائج الاختبارات في الملف: " + logFileName);

        } catch (IOException e) {
            System.err.println("خطأ أثناء إنشاء ملف النتائج: " + e.getMessage());
        }


        System.out.println("\n==================================================");
        System.out.println("ملخص نتائج الاختبارات:");
        System.out.println("عدد الاختبارات التي تم تشغيلها: " + summary.getTestsFoundCount());
        System.out.println("عدد الاختبارات الناجحة: " + summary.getTestsSucceededCount());
        System.out.println("عدد الاختبارات الفاشلة: " + summary.getTestsFailedCount());
        System.out.println("==================================================");
    }
}