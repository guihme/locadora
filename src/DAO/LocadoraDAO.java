package DAO;

import DTO.Locadora;
import DTO.LocadoraDTO;
import DTO.LocalizacaoDTO;
import DTO.VeiculoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LocadoraDAO implements Locadora{

    Connection con;
    PreparedStatement stm;
    ResultSet res;
    int idMaximo = -1;
    ArrayList<LocadoraDTO> lista = new ArrayList<>();
    ArrayList<VeiculoDTO> listaVeiculo = new ArrayList<>();

    /**
     *
     * @param locadora
     * @return
     */
    @Override
    public ResultSet autenticar(LocadoraDTO locadora) {
        String sql = "select * FROM locadora where usuario = ? and senha = ?";
        con = new ConexaoDAO().conexao();
        try {
            stm = con.prepareStatement(sql);

            stm.setString(1, locadora.getUsuario());
            stm.setString(2, locadora.getSenha());

            res = stm.executeQuery();

            return res;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Autenticar:" + e);

            return null;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int maiorIdLocadora() {
        String sql = "select * from locadora where id=(select max(id) from locadora)";
        con = new ConexaoDAO().conexao();
        try {
            stm = con.prepareStatement(sql);
            res = stm.executeQuery();

            res.next();

            idMaximo = res.getInt("id");
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Pesquisar:" + e);

        }
        return idMaximo;
    }

    /**
     *
     * @return
     */
    @Override
    public int maiorIdVeiculo() {
        String sql = "select * from veiculo where id=(select max(id) from veiculo)";
        con = new ConexaoDAO().conexao();
        try {
            stm = con.prepareStatement(sql);
            res = stm.executeQuery();

            res.next();

            idMaximo = res.getInt("id");
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Maior id veiculo:" + e);

        }
        return idMaximo;
    }

    /**
     *
     * @param locadora
     * @param localizacao
     */
    @Override
    public void cadastrarLocadora(LocadoraDTO locadora, LocalizacaoDTO localizacao) {
        if (locadora.getUsuario().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Usuario é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (locadora.getSenha().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Senha é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (locadora.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Nome é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (locadora.getCnpj().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo CNPJ é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (locadora.getTelefone().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Telefone é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (localizacao.getEndereco().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Endereço é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (localizacao.getBairro().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Bairro é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (localizacao.getCidade().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Cidade é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        if (localizacao.getEstado().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Estado é obrigatório", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }
        /* if(locadora.getUsuario().equals("")|| locadora.getSenha().equals("")|| locadora.getNome().equals("")|| locadora.getCnpj().equals("") || locadora.getTelefone().equals("")|| localizacao.getEndereco().equals("")|| localizacao.getBairro().equals("")|| localizacao.getCidade().equals("")|| localizacao.getEstado().equals("")){
           JOptionPane.showMessageDialog(null,"Preencha todos os campos", "Aviso", JOptionPane.WARNING_MESSAGE);
           return;
       }*/

        try {
            String sql = "insert into locadora (id, usuario, senha, nome, cnpj, telefone,endereco, bairro, cidade, estado) VALUES(?,?,?,?,?,?,?,?,?,?)";
            con = new ConexaoDAO().conexao();
            stm = con.prepareStatement(sql);

            stm.setInt(1, locadora.getId());
            stm.setString(2, locadora.getUsuario());
            stm.setString(3, locadora.getSenha());
            stm.setString(4, locadora.getNome());
            stm.setString(5, locadora.getCnpj());
            stm.setString(6, locadora.getTelefone());
            stm.setString(7, localizacao.getEndereco());
            stm.setString(8, localizacao.getBairro());
            stm.setString(9, localizacao.getCidade());
            stm.setString(10, localizacao.getEstado());

            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cadastrar Locadora" + e);
        }
    }

    /**
     *
     * @param idLocadora
     * @param veiculo
     */
    @Override
    public void cadastrarVeiculo(int idLocadora, VeiculoDTO veiculo) {
        if (veiculo.getMarca().equals("") || veiculo.getModelo().equals("") || veiculo.getAno().equals("") || veiculo.getAcessorios().equals("") || veiculo.getPreco().equals("") || veiculo.getCategoria().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso", JOptionPane.WARNING_MESSAGE);
            requestFocus();
            return;
        }

        try {
            String sql = "insert into veiculo (id, id_locadora, marca, modelo, ano, acessorios, preco, categoria) values (?,?,?,?,?,?,?,?)";
            con = new ConexaoDAO().conexao();
            stm = con.prepareStatement(sql);

            stm.setInt(1, veiculo.getId());
            stm.setInt(2, idLocadora);
            stm.setString(3, veiculo.getMarca());
            stm.setString(4, veiculo.getModelo());
            stm.setString(5, veiculo.getAno());
            stm.setString(6, veiculo.getAcessorios());
            stm.setString(7, veiculo.getPreco());
            stm.setString(8, veiculo.getCategoria());

            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Veiculo não cadastrado" + e);
        }
    }

    /**
     *
     * @param idLocadora
     * @return
     */
    @Override
    public ArrayList<VeiculoDTO> PesquisarVeiculoLocadora(int idLocadora) {
        String sql = "select * FROM veiculo where id_locadora = ?";
        con = new ConexaoDAO().conexao();
        ArrayList<VeiculoDTO> veiculosPesquisados = new ArrayList<>();

        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, idLocadora);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                veiculo.setId(rs.getInt("id"));
                veiculo.setId_locadora(rs.getInt("id_locadora"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setAcessorios(rs.getString("acessorios"));
                veiculo.setCategoria(rs.getString("categoria"));
                veiculo.setPreco(rs.getString("preco"));

                veiculosPesquisados.add(veiculo);
            }
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Pesquisar:" + e);

        }
        return veiculosPesquisados;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<VeiculoDTO> listarVeiculos() {
        String sql = "select * FROM veiculo";
        con = new ConexaoDAO().conexao();

        try {
            stm = con.prepareStatement(sql);
            res = stm.executeQuery();

            while (res.next()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                veiculo.setId(res.getInt("id"));
                veiculo.setId_locadora(res.getInt("id_locadora"));
                veiculo.setMarca(res.getString("marca"));
                veiculo.setModelo(res.getString("modelo"));
                veiculo.setAno(res.getString("ano"));
                veiculo.setAcessorios(res.getString("acessorios"));
                veiculo.setCategoria(res.getString("categoria"));
                veiculo.setPreco(res.getString("preco"));

                listaVeiculo.add(veiculo);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Pesquisar:" + e);

        }
        return listaVeiculo;
    }

    /**
     *
     * @param idLocadora
     * @return
     */
    @Override
    public LocadoraDTO encontrarLocadora(int idLocadora) {
        String sql = "select * FROM locadora where id = ?";
        con = new ConexaoDAO().conexao();
        LocadoraDTO locadora = new LocadoraDTO();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, idLocadora);
            res = stm.executeQuery();

            res.next();

            LocalizacaoDTO localizacao = new LocalizacaoDTO();
            locadora.setId(res.getInt("id"));
            locadora.setNome(res.getString("nome"));
            locadora.setCnpj(res.getString("cnpj"));
            locadora.setTelefone(res.getString("telefone"));
            localizacao.setEndereco(res.getString("endereco"));
            localizacao.setBairro(res.getString("bairro"));
            localizacao.setCidade(res.getString("cidade"));
            localizacao.setEstado(res.getString("estado"));
            locadora.setLocalizacao(localizacao);

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Pesquisar Locadora:" + e);

        }
        return locadora;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public VeiculoDTO buscarVeiculo(int id) {
        String sql = "select * FROM veiculo where id = ?";
        con = new ConexaoDAO().conexao();
        VeiculoDTO veiculo = new VeiculoDTO();
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            res = stm.executeQuery();
            res.next();
            veiculo.setId(res.getInt("id"));
            veiculo.setId_locadora(res.getInt("id_locadora"));
            veiculo.setMarca(res.getString("marca"));
            veiculo.setModelo(res.getString("modelo"));
            veiculo.setAno(res.getString("ano"));
            veiculo.setAcessorios(res.getString("acessorios"));
            veiculo.setCategoria(res.getString("categoria"));
            veiculo.setPreco(res.getString("preco"));

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Buscar Veiculo:" + e);

        }
        return veiculo;
    }

    /**
     *
     * @param veiculo
     */
    @Override
    public void editarVeiculo(VeiculoDTO veiculo) {
        String sql = "update veiculo set marca = ?, modelo = ?, ano = ?, acessorios = ?, preco = ?, categoria = ? where id = ?";
        con = new ConexaoDAO().conexao();

        try {
            stm = con.prepareStatement(sql);

            stm.setString(1, veiculo.getMarca());
            stm.setString(2, veiculo.getModelo());
            stm.setString(3, veiculo.getAno());
            stm.setString(4, veiculo.getAcessorios());
            stm.setString(5, veiculo.getPreco());
            stm.setString(6, veiculo.getCategoria());
            stm.setInt(7, veiculo.getId());

            stm.execute();
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Editar Veiculo" + e);
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void deletarVeiculo(int id) {
        String sql = "delete FROM veiculo where id = ?";
        con = new ConexaoDAO().conexao();

        try {
            stm = con.prepareStatement(sql);

            stm.setInt(1, id);

            stm.execute();
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Buscar Veiculo:" + e);

        }

    }

    public ArrayList<VeiculoDTO> fitrarPorVeiculoLocadora(int idLocadora, String coluna, String pesquisa) {
        String sql = String.format("select * FROM veiculo where id_locadora = ? and %s like ?", coluna);
        con = new ConexaoDAO().conexao();

        try {
            stm = con.prepareStatement(sql);

            stm.setInt(1, idLocadora);
            stm.setString(2, "%" + pesquisa + "%");

            res = stm.executeQuery();

            while (res.next()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                veiculo.setId(res.getInt("id"));
                veiculo.setId_locadora(res.getInt("id_locadora"));
                veiculo.setMarca(res.getString("marca"));
                veiculo.setModelo(res.getString("modelo"));
                veiculo.setAno(res.getString("ano"));
                veiculo.setAcessorios(res.getString("acessorios"));
                veiculo.setCategoria(res.getString("categoria"));
                veiculo.setPreco(res.getString("preco"));

                listaVeiculo.add(veiculo);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Pesquisar:" + e);

        }
        return listaVeiculo;
    }

    public ArrayList<LocadoraDTO> fitrarPorLocadora(String coluna, String pesquisa) {
        String sql = String.format("select * FROM locadora where %s like ?", coluna);
        con = new ConexaoDAO().conexao();

        try {
           
            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1, "%" + pesquisa + "%");

            ResultSet rs = pstm.executeQuery();
           
            while (rs.next()) {
                LocadoraDTO locadora = new LocadoraDTO();
                LocalizacaoDTO localizacao = new LocalizacaoDTO();
                locadora.setId(rs.getInt("id"));
                locadora.setNome(rs.getString("nome"));
                locadora.setCnpj(rs.getString("cnpj"));
                locadora.setTelefone(rs.getString("telefone"));
                localizacao.setEndereco(rs.getString("endereco"));
                localizacao.setBairro(rs.getString("bairro"));
                localizacao.setCidade(rs.getString("cidade"));
                localizacao.setEstado(rs.getString("estado"));
                locadora.setLocalizacao(localizacao);
                locadora.setVeiculos(PesquisarVeiculoLocadora(locadora.getId()));

                if (locadora.getVeiculos().size() > 0) {
                    lista.add(locadora);
                }
               
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Buscar Locadoras:" + e);

        }

        return lista;
    }

    public ArrayList<VeiculoDTO> fitrarPorVeiculo(String coluna, String pesquisa) {
        String sql = String.format("select * FROM veiculo where %s like ?", coluna);
        con = new ConexaoDAO().conexao();

        try {
            stm = con.prepareStatement(sql);

            stm.setString(1, "%" + pesquisa + "%");

            res = stm.executeQuery();

            while (res.next()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                veiculo.setId(res.getInt("id"));
                veiculo.setId_locadora(res.getInt("id_locadora"));
                veiculo.setMarca(res.getString("marca"));
                veiculo.setModelo(res.getString("modelo"));
                veiculo.setAno(res.getString("ano"));
                veiculo.setAcessorios(res.getString("acessorios"));
                veiculo.setCategoria(res.getString("categoria"));
                veiculo.setPreco(res.getString("preco"));

                listaVeiculo.add(veiculo);
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "FuncionarioDAO Pesquisar:" + e);

        }
        return listaVeiculo;
    }

    private void requestFocus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
