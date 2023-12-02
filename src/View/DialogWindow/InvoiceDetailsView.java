package View.DialogWindow;

import javax.swing.*;
import java.awt.*;

public class InvoiceDetailsView extends JDialog {

    private JTextField customerNameField;
    private JTextField amountPaidField;
    private JTextField invoiceAmountField;
    private JTextField changeField;
    private JButton generateInvoiceButton;

    public InvoiceDetailsView(Frame owner, boolean modal) {
        super(owner, modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        this.setLayout(new BorderLayout());
        this.setSize(400, 200);

        // Initializing components
        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        JLabel amountPaidLabel = new JLabel("Amount Paid:");
        amountPaidField = new JTextField(20);
        JLabel invoiceAmountLabel = new JLabel("Invoice Amount:");
        invoiceAmountField = new JTextField(Double.toString(0));
        invoiceAmountField.setEditable(false);
        JLabel changeLabel = new JLabel("Change:");
        changeField = new JTextField(20);
        changeField.setEditable(false);
        generateInvoiceButton = new JButton("Generate Invoice");

        // Layout components
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2));
        fieldsPanel.add(customerNameLabel);
        fieldsPanel.add(customerNameField);
        fieldsPanel.add(amountPaidLabel);
        fieldsPanel.add(amountPaidField);
        fieldsPanel.add(invoiceAmountLabel);
        fieldsPanel.add(invoiceAmountField);
        fieldsPanel.add(changeLabel);
        fieldsPanel.add(changeField);

        this.add(fieldsPanel, BorderLayout.CENTER);
        this.add(generateInvoiceButton, BorderLayout.SOUTH);
    }

    // Getters for the fields and button
    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getAmountPaidField() {
        return amountPaidField;
    }

    public JTextField getInvoiceAmountField() {
        return invoiceAmountField;
    }

    public JTextField getChangeField() {
        return changeField;
    }

    public JButton getGenerateInvoiceButton() {
        return generateInvoiceButton;
    }
}
