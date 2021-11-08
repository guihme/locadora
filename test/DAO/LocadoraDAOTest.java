/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.VeiculoDTO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author layan
 */
public class LocadoraDAOTest {
    
    public LocadoraDAOTest() {
    }

    
    @Test
    public void testCadastroVeiculos1() {
        LocadoraDAO dao = new LocadoraDAO();
        VeiculoDTO carro1 = new VeiculoDTO("Volkswagem", "Gol", "2020", "Trava", "40000", "popular");
        dao.cadastrarVeiculo(3, carro1);
        assertNotNull(carro1);
    }

   @Test(expected= UnsupportedOperationException.class)
    public void testCadastrarVeiculo2() {
        LocadoraDAO dao = new LocadoraDAO();
        VeiculoDTO carro2 = new VeiculoDTO("", "", "", "", "", "");
        dao.cadastrarVeiculo(3, carro2);
        
    }

  
    @Test
    public void testListarVeiculos() {
        LocadoraDAO dao = new LocadoraDAO();
        assertEquals(dao.listaVeiculo, dao.listarVeiculos());
    }

   
}
