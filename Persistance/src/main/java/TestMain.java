

import RequestModel.RequestProdus;
import Service.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanXML.xml");
        Service service=context.getBean("Service",Service.class);
        System.out.println(service.getIstoricComenzi(1));
        //System.out.println(service.getStocProdus("Parbriz",500));
        //service.adaugaProdus("Parbriz","Parbriz pentru BMW",500,2,2,15);
        RequestProdus p=new RequestProdus();

        //System.out.println(service.getStocProdus("Parbriz",500));
    }
}
