package com.example.reports;

/**
 * ReportProxy - Implements Proxy responsibilities:
 * - access check
 * - lazy loading of RealReport
 * - caching of RealReport within the same proxy
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    
    // Lazy-loaded real report - cached for repeated views
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // Check access control first
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: User " + user.getName() + " (role: " + user.getRole() 
                    + ") cannot access report " + reportId + " (classification: " + classification + ")");
            return;
        }
        
        // Lazy load the real report - only create when needed
        if (realReport == null) {
            System.out.println("[proxy] Creating real report for: " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] Reusing cached real report for: " + reportId);
        }
        
        // Delegate to real report
        realReport.display(user);
    }
}
