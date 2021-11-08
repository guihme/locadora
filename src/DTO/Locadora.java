/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author guilh
 */
public interface  Locadora {
  
    public ResultSet autenticar(LocadoraDTO locadora);
    public int maiorIdLocadora();
    public int maiorIdVeiculo();
    public void cadastrarLocadora(LocadoraDTO locadora, LocalizacaoDTO localizacao);
    public void cadastrarVeiculo(int idLocadora, VeiculoDTO veiculo);
    public ArrayList<VeiculoDTO> PesquisarVeiculoLocadora(int idLocadora);
    public ArrayList<VeiculoDTO> listarVeiculos();
    public LocadoraDTO encontrarLocadora(int idLocadora);
    public VeiculoDTO buscarVeiculo(int id);
    public void editarVeiculo(VeiculoDTO veiculo);
    public void deletarVeiculo(int id);
    
}
