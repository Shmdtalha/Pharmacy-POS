package View;

import Model.DAO.CustomerCartDAO;
import Model.Entity.CustomerCart;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportView extends BaseView{

    private CustomerCartDAO customerCartDAO = new CustomerCartDAO();
    private JPanel uiPanel;
    private JButton dailyButton, weeklyButton, monthlyButton, createReportButton;
    private JSpinner dateSpinner;
    private MonthlySpinnerModel monthlySpinnerModel;
    private String dateInMyFormatLower;
    private String dateInMyFormatUpper;
    public ReportView(String title) {
        super(title);
    }

    @Override
    protected void initializeComponents() {

        dailyButton = new JButton("Daily Report");
        weeklyButton = new JButton("Weekly Report");
        monthlyButton = new JButton("Monthly Report");
        createReportButton = new JButton("Create Report");
        uiPanel = new JPanel();

        dateSpinner = new JSpinner(new DailySpinnerModel());

        dailyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateSpinner.setModel(new DailySpinnerModel());
            }
        });

        weeklyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateSpinner.setModel(new WeeklySpinnerModel());
            }
        });

        monthlyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dateSpinner.setModel(new MonthlySpinnerModel());
            }
        });

        createReportButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                List<Transaction> transactions = new ArrayList<>();
                double totalSales = 0.0;
                int totalItemsSold = 0;


                try {

                    String reportType = "Daily";

                    if(dateSpinner.getModel() instanceof DailySpinnerModel){

                        reportType = "Daily";

                        String date = dateSpinner.getValue().toString();
                        System.out.println(date);

                        String date1 = String.valueOf(date.charAt(2));
                        date1 = date1.concat(String.valueOf(date.charAt(3)));
                        date1 = date1.concat(String.valueOf(date.charAt(5)));
                        date1 = date1.concat(String.valueOf(date.charAt(6)));
                        date1 = date1.concat(String.valueOf(date.charAt(8)));
                        date1 = date1.concat(String.valueOf(date.charAt(9)));

                        String idLower = date1.concat("000000");
                        String idUpper = date1.concat("235959");

                        List<CustomerCart> trans = customerCartDAO.getCustomerCart(idLower, idUpper);

                        for(int i = 0; i < trans.size(); i++){
                            String dateTrans = String.valueOf(trans.get(i).getCartId().charAt(4));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(5));
                            dateTrans = dateTrans.concat("-");
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(2));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(3));
                            dateTrans = dateTrans.concat("-");
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(0));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(1));

                            Transaction t = new Transaction(trans.get(i).getCartId(),
                                    dateTrans, trans.get(i).getCustomerName(),
                                    trans.get(i).getTotalAmount());

                            transactions.add(t);
                            totalSales += t.getAmount();
                        }
                    }
                    else if(dateSpinner.getModel() instanceof WeeklySpinnerModel){

                        reportType = "Weekly";

                        dateSpinner.getValue();
                        String date, datee;
                        date = dateInMyFormatLower;
                        datee = dateInMyFormatUpper;
                        System.out.println(date);
                        System.out.println(datee);

                        String idLower = date.concat("000000");
                        String idUpper = datee.concat("235959");

                        System.out.println(idLower + " " + idUpper);

                        List<CustomerCart> trans = customerCartDAO.getCustomerCart(idLower, idUpper);

                        for(int i = 0; i < trans.size(); i++){
                            String dateTrans = String.valueOf(trans.get(i).getCartId().charAt(4));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(5));
                            dateTrans = dateTrans.concat("-");
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(2));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(3));
                            dateTrans = dateTrans.concat("-");
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(0));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(1));

                            Transaction t = new Transaction(trans.get(i).getCartId(),
                                    dateTrans, trans.get(i).getCustomerName(),
                                    trans.get(i).getTotalAmount());

                            transactions.add(t);
                            totalSales += t.getAmount();
                        }
                    }
                    else if(dateSpinner.getModel() instanceof MonthlySpinnerModel){

                        reportType = "Monthly";

                        dateSpinner.getValue();
                        String date, datee;
                        date = dateInMyFormatLower;
                        datee = dateInMyFormatUpper;
                        System.out.println(date);
                        System.out.println(datee);

                        String idLower = date.concat("000000");
                        String idUpper = datee.concat("235959");

                        System.out.println(idLower + " " + idUpper);

                        List<CustomerCart> trans = customerCartDAO.getCustomerCart(idLower, idUpper);

                        for(int i = 0; i < trans.size(); i++){
                            String dateTrans = String.valueOf(trans.get(i).getCartId().charAt(4));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(5));
                            dateTrans = dateTrans.concat("-");
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(2));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(3));
                            dateTrans = dateTrans.concat("-");
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(0));
                            dateTrans = dateTrans + String.valueOf(trans.get(i).getCartId().charAt(1));

                            Transaction t = new Transaction(trans.get(i).getCartId(),
                                    dateTrans, trans.get(i).getCustomerName(),
                                    trans.get(i).getTotalAmount());

                            transactions.add(t);
                            totalSales += t.getAmount();
                        }
                    }
                    else{
                        return;
                    }


                    JRDataSource dataSource = new JRBeanCollectionDataSource(transactions);

                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("TotalSales", totalSales); // Replace with the actual total sales value
                    parameters.put("TotalItemsSold", totalItemsSold); // Replace with the actual total items sold value
                    parameters.put("reportType", reportType);

                    // Compile and fill the report
                    JasperReport jasperReport = JasperCompileManager.compileReport("D:\\Programming\\UNI\\SCD Project\\Pharmacy-POS\\src\\View\\report.jrxml");
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

                    // Display report
                    JRViewer viewer = new JRViewer(jasperPrint);
                    JFrame reportFrame = new JFrame("Report");
                    reportFrame.add(viewer);
                    reportFrame.setSize(800, 600);
                    reportFrame.setLocationRelativeTo(null);
                    reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    reportFrame.setVisible(true);
                }
                catch (JRException ex) {
                    ex.printStackTrace();
                }
//                catch (Exception ex) {
//                    ex.printStackTrace();
//                }
            }

        });

        uiPanel.add(dailyButton);
        uiPanel.add(weeklyButton);
        uiPanel.add(monthlyButton);
        uiPanel.add(dateSpinner);
        uiPanel.add(createReportButton);
        add(uiPanel);
    }

    private class DailySpinnerModel extends AbstractSpinnerModel {
        private Calendar calendar;
        private SimpleDateFormat dailyFormat = new SimpleDateFormat("yyyy-MM-dd");

        public DailySpinnerModel() {
            calendar = Calendar.getInstance();
        }

        @Override
        public Object getValue() {
            return dailyFormat.format(calendar.getTime());
        }

        @Override
        public void setValue(Object value) {
            if (value instanceof Date) {
                calendar.setTime((Date) value);
                fireStateChanged();
            }
        }

        @Override
        public Object getNextValue() {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return calendar.getTime();
        }

        @Override
        public Object getPreviousValue() {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return calendar.getTime();
        }
    }

    private class WeeklySpinnerModel extends AbstractSpinnerModel {
        private Calendar calendar;
        private SimpleDateFormat weeklyFormat = new SimpleDateFormat("yyyy 'Week' w");

        public WeeklySpinnerModel() {
            calendar = Calendar.getInstance();
        }

        @Override
        public Object getValue() {
            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat format_dayCheck = new SimpleDateFormat("EEE");

            calendar.setFirstDayOfWeek(Calendar.MONDAY);

            while(!format_dayCheck.format(calendar.getTime()).toString().equals("Mon")) {
                calendar.add(calendar.DAY_OF_WEEK, -1);
                System.out.println(calendar.getTime());
            }
            System.out.println("");
            dateInMyFormatLower = format.format(calendar.getTime());

            while(!format_dayCheck.format(calendar.getTime()).toString().equals("Sun")) {
                calendar.add(calendar.DAY_OF_WEEK, 1);
                System.out.println(calendar.getTime());
            }

            dateInMyFormatUpper = format.format(calendar.getTime());

            return weeklyFormat.format(calendar.getTime());
        }

        @Override
        public void setValue(Object value) {
            if (value instanceof Date) {
                calendar.setTime((Date) value);
                fireStateChanged();
            }
        }

        @Override
        public Object getNextValue() {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            return calendar.getTime();
        }

        @Override
        public Object getPreviousValue() {
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            return calendar.getTime();
        }
    }

    private class MonthlySpinnerModel extends AbstractSpinnerModel {
        private Calendar calendar;
        private SimpleDateFormat monthlyFormat = new SimpleDateFormat("yyyy-MM");

        public MonthlySpinnerModel() {
            calendar = Calendar.getInstance();
        }

        @Override
        public Object getValue() {

            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat format_dayCheck = new SimpleDateFormat("dd");

            while(!format_dayCheck.format(calendar.getTime()).toString().equals("01")) {
                calendar.add(calendar.DAY_OF_MONTH, -1);
                System.out.println(calendar.getTime());
            }
            System.out.println("");
            dateInMyFormatLower = format.format(calendar.getTime());
            boolean checkSomething = false;

            do{
                calendar.add(calendar.DAY_OF_MONTH, 1);
                if(!checkSomething || !format_dayCheck.format(calendar.getTime()).toString().equals("01")) {
                    System.out.println(calendar.getTime());
                    dateInMyFormatUpper = format.format(calendar.getTime());
                    checkSomething = true;
                }
            }while(!format_dayCheck.format(calendar.getTime()).toString().equals("01"));

            calendar.add(calendar.DAY_OF_MONTH, -1);

            return monthlyFormat.format(calendar.getTime());
        }

        @Override
        public void setValue(Object value) {
            if (value instanceof Date) {
                calendar.setTime((Date) value);
                fireStateChanged();
            }
        }

        @Override
        public Object getNextValue() {
            calendar.add(Calendar.MONTH, 1);
            return calendar.getTime();
        }

        @Override
        public Object getPreviousValue() {
            calendar.add(Calendar.MONTH, -1);
            return calendar.getTime();
        }
    }

    public class Transaction {
        private String transactionId;
        private String transactionDate;
        private String customerName;
        private double amount;

        public Transaction(){

        }

        public Transaction(String transactionId, String transactionDate, String customerName, double amount) {
            this.transactionId = transactionId;
            this.transactionDate = transactionDate;
            this.customerName = customerName;
            this.amount = amount;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }


}