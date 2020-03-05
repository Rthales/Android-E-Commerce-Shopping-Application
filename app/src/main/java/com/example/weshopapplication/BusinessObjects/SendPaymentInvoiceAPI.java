package com.example.weshopapplication.BusinessObjects;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// Author of Application/Class: Sabin Constantin Lungu
// Purpose of Application/Class: This class uses the JavaMail API that automatically sends an e-mail to the user after they have purchased a specific product(s)
// Date of Last Modification: 19/02/2020
// Any Bugs? None

public class SendPaymentInvoiceAPI extends AsyncTask<Void, Void, Void> { // The class inherits from an Asynchronous Task with 3 parameters initially Void
    private Context context; // The context
    private Session session;

    private String email; // The e-mail to send to
    private String subject; // The subject
    private String emailContent; // The content of the e-mail to send (The product order)

    private String cardNumber;
    private String cardCVV;
    private String cardHolderName;

    private String expiryMonth;
    private String expiryYear;

    private ProgressDialog dialog;

    public SendPaymentInvoiceAPI(Context context, Session session, String email, String subject, String emailContent, String cardNumber, String cardCVV, String cardHolderName, String expiryMonth, String expiryYear, ProgressDialog dialog) {
        this.context = context;
        this.session = session;
        this.email = email;
        this.subject = subject;
        this.emailContent = emailContent;
        this.cardNumber = cardNumber;
        this.cardCVV = cardCVV;
        this.cardHolderName = cardHolderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.dialog = dialog;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public ProgressDialog getDialog() {
        return dialog;
    }

    public void setDialog(ProgressDialog dialog) {
        this.dialog = dialog;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Invoice Sent", "Please wait..", false, false);
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        dialog.dismiss();
        Toast.makeText(context, "Invoice Sent Successfully", Toast.LENGTH_SHORT).show();
    }

    protected Void doInBackground(Void... params) {
        Properties properties = new Properties(); // Create a new properties instance

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthenticator() {
                return new PasswordAuthentication(MailCredentialsAPI.EMAIL_ADDRESS, MailCredentialsAPI.PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MailCredentialsAPI.EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailContent));

            message.setSubject(subject);
            message.setText(emailContent);

            Transport.send(message);
        } catch (MessagingException exc) {
            exc.printStackTrace();
        }

        return null; // Return nothing otherwise
    }
}