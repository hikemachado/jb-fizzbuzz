package io.hike.usecases;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;

import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.hike.service.FizzBuzzService;

@ApplicationScoped
public class TreasureUseCase {

    Logger logger = LoggerFactory.getLogger(TreasureUseCase.class);

    @Inject
    FizzBuzzService fizzBuzzService;
    
    public String discoverTreasure(){

        boolean treasureFound = false;
        while(!treasureFound){
            String shaHash = "";
            try{
                Long[] numbers = fizzBuzzService.getFizzBuzz();
                List<String> fizzBuzzList = fizzBuzzService.playFizzBuzz(numbers);
                shaHash = fizzBuzzService.getShaHash(fizzBuzzList);
                fizzBuzzService.postFizzBuzz(shaHash, fizzBuzzList);
                fizzBuzzService.getTreasure(shaHash);
                return "{\"result\":\"TREASURE FOUND!\"}";
            }catch (ValidationException ve){
                return "{\"result\": \" error - "+ve.getMessage()+"\"}";
            }catch (BadRequestException be){
                //Clear process again treasure not found
                logger.info(be.getMessage());
                fizzBuzzService.flushFizzBuzz(shaHash);
            }catch (WebApplicationException e){
                //resilience - keep trying
                logger.info(e.getResponse().getStatus()+"");
                
            }
        }
        return "{\"result\": \" error - Something went wrong!!\"}";
    }

    public String resetTreasure(){
        logger.info("Reseting");
        try {
            fizzBuzzService.resetFizzBuzz();
        } catch (Exception e) {
            return "{\"result\": \" error - "+e.getMessage()+"\"}";
        }
        return "{\"result\":\"Reset - Play Again\"}";
    }
}
