package com.example.reports;

/**
 * ReportViewer - Updated to work with Report interface (Proxy pattern).
 * Clients now use ReportProxy instead of directly using ReportFile.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
