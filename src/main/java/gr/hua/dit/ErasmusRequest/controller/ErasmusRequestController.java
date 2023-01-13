package gr.hua.dit.ErasmusRequest.controller;

import com.sendgrid.Response;
import gr.hua.dit.ErasmusRequest.model.EmailRequest;
import gr.hua.dit.ErasmusRequest.model.ErasmusRequest;
import gr.hua.dit.ErasmusRequest.service.EmailService;
import gr.hua.dit.ErasmusRequest.service.ErasmusRequestService;
import gr.hua.dit.ErasmusRequest.service.ErasmusCommitteeService;
import gr.hua.dit.ErasmusRequest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
@CrossOrigin(origins="http://localhost:3000")
public class ErasmusRequestController {
        @Autowired
        ErasmusRequestService erasmusRequestService;

        @Autowired
        ErasmusCommitteeService erasmusCommitteeService;

        @Autowired
        UserService userService;

        @Autowired
        EmailService emailService;

        @GetMapping("/requests")
        public ResponseEntity<List<ErasmusRequest>> getAllErasmusRequest() {
            return ResponseEntity.ok().body(erasmusRequestService.findAllErasmusRequest());

        }

        @GetMapping("/request/owned")
        @PostFilter("filterObject.createdBy==authentication.name")
        public List<ErasmusRequest> getErasmusRequestOwnedBy() {
            return erasmusRequestService.findAllErasmusRequest();

        }


        //USE IN SYSTEM FOR GETTING ALL REQ SEND TO THE CONNECTED TEACHER
        @GetMapping("/requests/sendTo")
        @PostFilter("filterObject.sendTo==authentication.name")
        public List<ErasmusRequest> getErasmusRequestSentTo() {
            return erasmusRequestService.findAllErasmusRequest();

        }
        @PostMapping("/request/form")
        public ResponseEntity<ErasmusRequest> saveErasmusRequest(@RequestBody ErasmusRequest newErasmusRequest, Authentication auth) {
            System.out.println((newErasmusRequest.getId())+"  "+auth.getName());
            for(int i = 0; i< erasmusRequestService.findAllErasmusRequest().size(); i++){
                if(Objects.equals(newErasmusRequest.getCreatedBy(), erasmusRequestService.findAllErasmusRequest().get(i).getCreatedBy())) {
                    if (Objects.equals(newErasmusRequest.getSendTo(), erasmusRequestService.findAllErasmusRequest().get(i).getSendTo())) {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
                    }
                }
            }

            return ResponseEntity.ok().body(ErasmusRequestService.saveErasmusRequest(newErasmusRequest, auth.getName()));

        }

        @GetMapping("/requests/sendTo/{id}")
        @PostFilter("filterObject.sendTo==authentication.name")
        public List<ErasmusRequest> getErasmusRequestByCreatedId(@PathVariable("id") String createdBy) {
            return erasmusRequestService.findErasmusRequestByCreatedByAndStatus(createdBy,"null");

        }

        /*
        @GetMapping("/requests/sendTo/{id}")
        public ResponseEntity<RecommendationErasmusRequest> getErasmusRequestBySendId(@PathVariable("id") String sendTo) {
            return ResponseEntity.ok().body(erasmusRequestService.findErasmusRequestBySendTo(sendTo));

        }*/


        @PutMapping("/requests/sendTo/{id}")
        public ResponseEntity<ErasmusRequest> updateErasmusRequest(@PathVariable("id")  String createdBy, @RequestBody ErasmusRequest newErasmusRequest, Authentication auth) {
            return ResponseEntity.ok().body(erasmusRequestService.updateErasmusRequest(createdBy,newErasmusRequest,auth.getName()));

        }

        @PostMapping("/requests/approved")
        public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
            Response response = emailService.sendEmail(emailRequest);
            if(response.getStatusCode()==200||response.getStatusCode()==202){
                return new ResponseEntity<>("Send successfully!",HttpStatus.OK);

            }

            return new ResponseEntity<>("Failed to send Email!",HttpStatus.NOT_FOUND);
        }

        @GetMapping("/requests/approved")
        @PostFilter("filterObject.sendTo==authentication.name")
        public List<ErasmusRequest> getApprovedReqs() {
            return erasmusRequestService.findErasmusRequestByStatus("APPROVED");

        }

        @GetMapping("/requests/approved/{id}")
        public List<Object> getApprovedRequest(@PathVariable("id") String createdBy,Authentication auth) {

            //returns only info about the logged in teacher by checking his auth name without postfilter cause our returning object isnt a specific one
            for(int i = 0; i< erasmusRequestService.findErasmusRequestByCreatedByAndStatus(createdBy,"APPROVED").size(); i++){
                if(Objects.equals(erasmusRequestService.findErasmusRequestByCreatedByAndStatus(createdBy, "APPROVED").get(i).getSendTo(), auth.getName())){
                    List<Object> list = new ArrayList<Object>();
                    list.addAll(erasmusRequestService.findErasmusRequestByCreatedByAndStatus(createdBy,"APPROVED"));
                    list.addAll(erasmusCommitteeService.findErasmusCommitteByField(erasmusRequestService.findErasmusRequestByCreatedByAndStatus(createdBy,"APPROVED").get(0).getField()));
                    return list;
                }
            }

            return null;


        }


}
