package com.example.weshopapplication;

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

public class SendInvoiceAPI extends AsyncTask<Void, Void, Void> {
    private Context context;
    private Session session;

    private String email;
    private String subject;
    private String emailContent;

    private ProgressDialog dialog;

    public SendInvoiceAPI(Context context, Session session, String email, String subject, String emailContent, ProgressDialog dialog) {
        this.context = context;
        this.session = session;
        this.email = email;
        this.subject = subject;
        this.emailContent = emailContent;
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

        return null;
    }
}
