/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ricardo
 */
@ManagedBean(name = "controleSeguro")
@SessionScoped
public class ControleSeguro implements Serializable{
    
    private SeguroDAO dao;
    private Seguro objeto;
    private CorretorDAO daoCorretor;
    private CarroDAO daoCarro;
    
    
     public ControleSeguro(){
        dao = new SeguroDAO();
        daoCorretor = new CorretorDAO();
        daoCarro = new CarroDAO();
    }
    
    public String listar(){
        return"/privado/seguro/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Seguro();
        return "formulario";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        }else{
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try{
            objeto = dao.localizar(id);
            return "formulario";
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    

    public SeguroDAO getDao() {
        return dao;
    }

    public void setDao(SeguroDAO dao) {
        this.dao = dao;
    }

    public Seguro getObjeto() {
        return objeto;
    }

    public void setObjeto(Seguro objeto) {
        this.objeto = objeto;
    }

    public CorretorDAO getDaoCorretor() {
        return daoCorretor;
    }

    public void setDaoCorretor(CorretorDAO daoCorretor) {
        this.daoCorretor = daoCorretor;
    }

    public CarroDAO getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDAO daoCarro) {
        this.daoCarro = daoCarro;
    }

    
    
    
    
}
