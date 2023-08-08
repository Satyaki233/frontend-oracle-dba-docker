package com.rcciit.project.backend.service;


import com.rcciit.project.backend.POJO.Member;
import com.rcciit.project.backend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;
    
    
    
    
    
    public  ResponseEntity<Object> getValidateUser(Member member){
        List<Member> result=null;
        ResponseEntity<Object> re = null;
     
     
        try {
            result = memberRepository.loginMember(member);
            if(result.size() != 0){
            	
            	re = ResponseHandler.generateResponse(HttpStatus.OK,true,"User Registerd",result);
            }
        } catch (SQLException e) {
        	re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,e.toString(),null) ;
        }
        System.out.println("not working");
        return re;

    }
    
    

    //TODO : create an Object response
    public ResponseEntity<Object> registerMember(Member member){
        Member result=null;
        ResponseEntity<Object> re = null;
        try {
            result = memberRepository.registerMember(member);
            if(result.getId()!=null){
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"User Registerd",result);
            }
        } catch (SQLException e) {
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,e.toString(),null) ;
        }
        return re ;

    }

    //TODO : get List of all members
    public ResponseEntity<Object> getAllMembers(){
        List<Member> result = null;
        ResponseEntity<Object> re = null;
        try{
            result = memberRepository.getAllMember();
            if(result.size() != 0){
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Members found",result);
            }else{
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Empty List",null) ;
            }
        }catch (SQLException e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,e.toString(),null);
        }catch(Exception e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"Some Exception",null) ;
        }

        return re;
    }

    public ResponseEntity<Object> getVolunteer(){
        List<Member> result = null;
        ResponseEntity<Object> re = null;
        try{
            result = memberRepository.getVolunteer();
            if(result.size() != 0){
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Members found",result);
            }else{
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Empty List",null) ;
            }
        }catch (SQLException e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,e.toString(),null);
        }catch(Exception e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"Some Exception",null) ;
        }

        return re;
    }

    public ResponseEntity<Object> getParticipants(){
        List<Member> result = null;
        ResponseEntity<Object> re = null;
        try{
            result = memberRepository.getParticipants();
            if(result.size() != 0){
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Members found",result);
            }else{
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Empty List",null) ;

            }
        }catch (SQLException e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,e.toString(),null);
        }catch(Exception e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"Some Exception",null) ;
        }
        return re;
    }



    public ResponseEntity<Object> getAudience() {
        int result = 0;
        ResponseEntity<Object> re = null;
        try{
            result = memberRepository.getAudinece();
            if(result != 0){
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"Members found",result);
            }else{
                re = ResponseHandler.generateResponse(HttpStatus.OK,true,"No one is registered",null) ;

            }
        }catch (SQLException e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,e.toString(),null);
        }catch(Exception e){
            re = ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"Some Exception",null) ;
        }
        return re;
    }
}
