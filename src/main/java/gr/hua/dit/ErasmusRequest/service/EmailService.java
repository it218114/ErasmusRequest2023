package gr.hua.dit.ErasmusRequest.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import gr.hua.dit.ErasmusRequest.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {


    @Autowired
    SendGrid sendGrid;

    public Response sendEmail(EmailRequest emailRequest){

        Mail mail = new Mail(new Email("it218114@hua.gr"),emailRequest.getSubject(),new Email(emailRequest.getTo()),new Content("text/plain",emailRequest.getBody()));

        //mail.setReplyTo(new Email(emailRequest.getTo()));

        Request request = new Request();
        Personalization personalization = new Personalization();

        personalization.addTo(new Email(emailRequest.getTo()));

        mail.addPersonalization(personalization);
        //mail.setTemplateId(templateId);

        Response response = null;

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            response= sendGrid.api(request);


            //System.out.println(request.getBody());
            //System.out.println(response.getHeaders());
            //System.out.println(response.getBody());

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return response;
    }


}
