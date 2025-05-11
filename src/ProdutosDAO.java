/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){        
        conn = new conectaDAO().connectDB();
        String sql = "insert into produtos (nome, valor, status) values (?,?,?)";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o produto.");
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){   
        conn = new conectaDAO().connectDB();
        String sql = "select * from produtos";
        listagem.clear();
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));    
                
                listagem.add(produto);
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
        
        return listagem;
    }   
    
    public void venderProduto() {
        conn = new conectaDAO().connectDB();
        String sql = "set status = 'vendido' where id = ";
    }
}

