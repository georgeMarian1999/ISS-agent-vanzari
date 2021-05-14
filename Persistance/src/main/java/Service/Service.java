package Service;

import Model.*;
import Repo.*;
import RequestModel.RequestProdus;

import java.util.List;

public class Service {
    private FirmaRepository firmaRepository;
    private ProdusRepository produsRepository;
    private StocRepository stocRepository;
    private UserRepository userRepository;
    private ComandaRepository comandaRepository;


    public Service(FirmaRepository firmaRepository,ProdusRepository produsRepository,UserRepository userRepository,ComandaRepository comandaRepository,StocRepository stocRepository){
        this.firmaRepository=firmaRepository;
        this.produsRepository=produsRepository;
        this.userRepository=userRepository;
        this.comandaRepository=comandaRepository;
        this.stocRepository=stocRepository;
    }
    public User localLogin(String username,String password)throws RepositoryException{
        try{
            return this.userRepository.LocalLogin(username,password);
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public Firma findFirma(Integer integer){
       return this.firmaRepository.findOne(integer);
    }
    public List<Produs> getAllProducts()throws RepositoryException{
        return this.produsRepository.getAll();
    }
    public List<Produs> getAllProductsFirma(int firmaID)throws RepositoryException{
        return this.produsRepository.getAllFromFirma(firmaID);
    }
    public List<Comanda> getIstoricComenzi(int userID)throws RepositoryException{
        return this.comandaRepository.getIstoricComenzi(userID);
    }
    public List<Comanda> getComenziPrimite(int firmaID,Status status)throws RepositoryException{
        return this.comandaRepository.vizualizareComenziPrimite(firmaID,status);
    }
    public List<Comanda> getComenziPrimite(int firmaID)throws RepositoryException{
        return this.comandaRepository.vizualizareComenziPrimite(firmaID);
    }
    public int getStocProdus(int produsID)throws RepositoryException{
        return this.produsRepository.getStocProdus(produsID);
    }
    public void plasareComanda(int produsID,int cantitate,int userID,String data)throws RepositoryException{
        try{
            double valoare=this.produsRepository.getPretFromID(produsID)*cantitate;
            int comandaID=this.comandaRepository.getMaxID()+1;
            Comanda comandaNoua=new Comanda(comandaID,userID,produsID,data,cantitate,valoare,Status.Procesare);
            this.stocRepository.comandaPlasata(produsID,cantitate);
            this.comandaRepository.plasareComanda(comandaNoua);
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }

    }
    public List<Comanda> vizualizareReturnari(int firmaID)throws RepositoryException{
        return this.comandaRepository.vizualizareReturnari(firmaID);
    }
    public void adaugaProdus(RequestProdus p,int userID) throws RepositoryException{
        try{
            int produsID=this.produsRepository.getMaxID()+1;
            System.out.println(this.firmaRepository.getFirmaIDFromUser(userID));
            Produs produsNou=new Produs(p.getNumeProdus(),produsID,p.getDescriereProdus(),p.getPret(),this.firmaRepository.getFirmaIDFromUser(userID));
            this.produsRepository.adaugareProdus(produsNou);
            this.stocRepository.adaugaProdus(userID,produsID,p.getCant());
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }

    public void confirmareComanda(int comandaID)throws RepositoryException{
        try{
            this.comandaRepository.confirmareComanda(comandaID);
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public void anulareComanda(int comandaID)throws RepositoryException{
        try{
            this.comandaRepository.anulareComanda(comandaID);
            this.stocRepository.adaugaStoc(this.comandaRepository.getComandaCant(comandaID),this.comandaRepository.getProdusIDFromComanda(comandaID));
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }

    }
    public void returnareComanda(int comandaID)throws RepositoryException{
        try{
            this.comandaRepository.returnareComanda(comandaID);
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }

    }
    public void confirmareReturnare(int comandaID)throws RepositoryException{
        try{
            this.comandaRepository.confirmareReturnare(comandaID);
            this.stocRepository.adaugaStoc(this.comandaRepository.getComandaCant(comandaID),this.comandaRepository.getProdusIDFromComanda(comandaID));
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
    public void adaugareStoc(int produsID,int cant)throws RepositoryException{
        try{
            this.stocRepository.adaugaStoc(cant,produsID);
        }catch (RepositoryException ex){
            throw new RepositoryException(ex.getMessage());
        }
    }
}
