package io.hike.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.common.hash.Hashing;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class FizzBuzzService {
    
    @Inject
    @RestClient
    JurosBaixosRemoteService jurosBaixosRemoteService;

    public List<String> playFizzBuzz(Long[] numbers){

        List<String> response = new ArrayList<String>();
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i]%15 == 0){
                // number divisible by 3 and 5 will
                // always be divisible by 15, print
                // 'FizzBuzz' in place of the number
                response.add("\"fizzbuzz\"");
            }else if(numbers[i]%3 == 0){
                // number divisible by 3? print 'Fizz'
                // in place of the number
                response.add("\"fizz\"");
            }else if(numbers[i]%5 == 0){
                // number divisible by 5, print 'Buzz' 
                // in place of the number
                response.add("\"buzz\"");
            }else{
                // print the number 
                response.add("\""+numbers[i].toString()+"\"");
            }
        }
        return response;
    }

    public Long[] getFizzBuzz(){
        return jurosBaixosRemoteService.getFizzBuzz();
    }

    public void postFizzBuzz(String shaHash, List<String> fizzBuzzList){
        jurosBaixosRemoteService
            .postFizzBuzz(shaHash, prepareFizzBuzzBody(fizzBuzzList));  
    }

    public void getTreasure(String shaHash){
        jurosBaixosRemoteService.getTreasure(shaHash);
    }

    public void flushFizzBuzz(String shaHash){
        try{
            jurosBaixosRemoteService.deleteFizzBuzz(shaHash);
        }catch(Exception e){
            System.out.println("Failed on delete!, keep trying...");
        }
    }

    public void resetFizzBuzz(){
        jurosBaixosRemoteService.reset();
    }

    public String getShaHash(List<String> fizzBuzzList){
        return Hashing.sha256()
                .hashString(minifyListAsString(fizzBuzzList), StandardCharsets.UTF_8)
                .toString();
    }

    private String[] prepareFizzBuzzBody(List<String> fizzBuzzList){
        //remove unnecessary characters and convert to array
        return minifyListAsString(fizzBuzzList).replace("[","").replace("]","").replace("\"", "").split(",");
    }
  
    private String minifyListAsString(List<String> fizzBuzzList){
        return fizzBuzzList.toString().replace(" ", "");
    }

}
