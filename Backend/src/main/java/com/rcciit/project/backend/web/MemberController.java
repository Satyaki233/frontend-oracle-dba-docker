package com.rcciit.project.backend.web;


import com.rcciit.project.backend.POJO.Member;
import com.rcciit.project.backend.service.MemberService;
import com.rcciit.project.backend.service.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/all")
    public ResponseEntity<Object> getMembers(){
    	System.out.println("kk");
        return memberService.getAllMembers();
    }

    
    
    
    @PostMapping("/login")
    public ResponseEntity<Object> ValidateloginValue(@RequestBody Member member) {
    	
    	 ResponseEntity<Object> re=  memberService.getValidateUser(member);
    	 System.out.println(re);
    	 if (re==null) {
    		 re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,true,"User Not Registerd",null);
    		 
    	 }
    	 return re;
    }
//
    @PostMapping("/register")
    public ResponseEntity<Object> registerMember(@RequestBody Member member){
        Random random = new Random();
        int x = random.nextInt(1000000) + 100;
        member.setId(Integer.toString(x));
        return memberService.registerMember(member);
    }
//
    @GetMapping("/participants")
    public ResponseEntity<Object> getParticipants(){
       return memberService.getParticipants();
    }
//
    @GetMapping("/volunteer")
    public ResponseEntity<Object> getVolunteer(){
        return memberService.getVolunteer();
    }

    @GetMapping("/audience")
    public ResponseEntity<Object> getAudience(){
        return  memberService.getAudience();
    }


}
