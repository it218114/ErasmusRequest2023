package gr.hua.dit.ErasmusRequest.controller;

import gr.hua.dit.ErasmusRequest.model.ErasmusCommittee;
import gr.hua.dit.ErasmusRequest.service.ErasmusCommitteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ErasmusCommitteeController {
        @Autowired
        ErasmusCommitteeService erasmusCommitteeService;

        //USED FOR TESTING
        @GetMapping("/ErasmusCommittee")
        public ResponseEntity<List<ErasmusCommittee>> getAllErasmusCommittee() {
            return ResponseEntity.ok().body(erasmusCommitteeService.findErasmusCommittee());

        }

        //USED IN SYSTEM
        @PostMapping("/ErasmusCommittee/add")
        public ResponseEntity<ErasmusCommittee> saveErasmusCommittee(@RequestBody ErasmusCommittee newErasmusCommittee, Authentication auth) {
            System.out.println(newErasmusCommittee.getName()+"  "+auth.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body((erasmusCommitteeService.saveErasmusCommittee(newErasmusCommittee)));

        }

        @GetMapping("/ErasmusCommittee/{field}")
        public List<ErasmusCommittee> getErasmusCommitteeByField(@PathVariable("field") String field) {
            return erasmusCommitteeService.findErasmusCommitteeByField(field);

        }




}
