package firma.services;

import Model.User;
import RequestModel.loginUser;
import Service.Service;
import Service.RepositoryException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firma/login")
public class LoginController {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanXML.xml");
    private Service service=context.getBean("Service",Service.class);

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin("*")
    public ResponseEntity<?> login(@RequestBody loginUser login){
        try{
            User user=service.localLogin(login.getUsername(),login.getPassword());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
