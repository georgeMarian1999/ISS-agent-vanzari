package firma.services;

import Model.Comanda;
import Model.Firma;
import Model.Produs;
import Model.Status;
import RequestModel.RequestComanda;
import RequestModel.RequestProdus;
import RequestModel.RequestStoc;
import RequestModel.ResponseProdus;
import Service.Service;
import Service.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/firma/operations")
public class FirmaController {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanXML.xml");
    private Service service=context.getBean("Service",Service.class);


    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(){
        try{
            List<ResponseProdus> produsList=new ArrayList<>();
            for(Produs p:service.getAllProducts()){
                produsList.add(new ResponseProdus(p,service.getStocProdus(p.getProdusID())));
            }
            return new ResponseEntity<>(produsList,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
    @RequestMapping(value = "/history/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getHistroy(@PathVariable int id){
        try{
            List<Comanda> comenzi=this.service.getIstoricComenzi(id);
            return new ResponseEntity<>(comenzi,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/ordersreceived/{id}/{type}",method = RequestMethod.GET)
    public ResponseEntity<?> getOrdersReceived(@PathVariable int id,@PathVariable String type){
        try{
            List<Comanda> comenzi=new ArrayList<>();
            if(type.equals("Procesare")){
                comenzi=this.service.getComenziPrimite(id,Status.Procesare);
            }
            if(type.equals("Confirmata")){
                 comenzi=this.service.getComenziPrimite(id,Status.Confirmata);
            }
            if(type.equals("Returnare")){
                 comenzi=this.service.getComenziPrimite(id,Status.Returnare);
            }
            if(type.equals("Returnata")){
                 comenzi=this.service.getComenziPrimite(id,Status.Returnata);
            }
            if(type.equals("Anulata")){
                 comenzi=this.service.getComenziPrimite(id,Status.Anulata);
            }
            return new ResponseEntity<>(comenzi,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/ordersreceived/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getOrdersReceived(@PathVariable int id){
        try{
            List<Comanda> comenzi=new ArrayList<>();
            comenzi=this.service.getComenziPrimite(id);
            return new ResponseEntity<>(comenzi,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/returns/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getReturns(@PathVariable int id){
        try{
            List<Comanda> comenzi=this.service.vizualizareReturnari(id);
            return new ResponseEntity<>(comenzi,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/addProduct/{id}",method = RequestMethod.POST)
    public ResponseEntity<?> addNewProduct(@RequestBody RequestProdus produs,@PathVariable int id){
        try{
            service.adaugaProdus(produs,id);
            return new ResponseEntity<>("Produs adaugat",HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductsFirma(@PathVariable int id){
        try{
            List<ResponseProdus> produse=new ArrayList<>();
            for(Produs p: service.getAllProductsFirma(id)){
                produse.add(new ResponseProdus(p, service.getStocProdus(p.getProdusID())));
            }
            return new ResponseEntity<>(produse,HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/addStoc",method = RequestMethod.POST)
    public ResponseEntity<?> adaugaStoc(@RequestBody RequestStoc stoc){
        try{
            service.adaugareStoc(stoc.getProdusID(),stoc.getCant());
            return new ResponseEntity<>("Stoc adaugat",HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/comanda",method = RequestMethod.POST)
    public ResponseEntity<?> plasareComanda(@RequestBody RequestComanda comanda){
        try{
            service.plasareComanda(comanda.getProdusID(), comanda.getCant(),comanda.getUserID(), comanda.getData());
            return new ResponseEntity<>("Comanda plasata",HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/anulareComanda/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> anulareComanda(@PathVariable int id) {
        try {
            service.anulareComanda(id);
            return new ResponseEntity<>("Comanda anulata", HttpStatus.OK);
        } catch (RepositoryException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
    @RequestMapping(value = "/confirmareComanda/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> confirmareComanda(@PathVariable int id){
        try{
            service.confirmareComanda(id);
            return new ResponseEntity<>("Comanda confirmata", HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/returnare/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> returnareComanda(@PathVariable int id){
        try{
            service.returnareComanda(id);
            return new ResponseEntity<>("Returnare in curs",HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/confirmareRetur/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> confirmareReturnare(@PathVariable int id){
        try{
            service.confirmareReturnare(id);
            return new ResponseEntity<>("Comanda returnata",HttpStatus.OK);
        }catch (RepositoryException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
