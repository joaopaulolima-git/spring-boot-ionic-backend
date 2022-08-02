package com.jpdev.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpdev.cursomc.domain.Categoria;
import com.jpdev.cursomc.domain.Cidade;
import com.jpdev.cursomc.domain.Cliente;
import com.jpdev.cursomc.domain.Endereco;
import com.jpdev.cursomc.domain.Estado;
import com.jpdev.cursomc.domain.ItemPedido;
import com.jpdev.cursomc.domain.Pagamento;
import com.jpdev.cursomc.domain.PagamentoComBoleto;
import com.jpdev.cursomc.domain.PagamentoComCartao;
import com.jpdev.cursomc.domain.Pedido;
import com.jpdev.cursomc.domain.Produto;
import com.jpdev.cursomc.enums.EstadoPagamento;
import com.jpdev.cursomc.enums.TipoCliente;
import com.jpdev.cursomc.repositories.CategoriaRepository;
import com.jpdev.cursomc.repositories.CidadeRepository;
import com.jpdev.cursomc.repositories.ClienteRepository;
import com.jpdev.cursomc.repositories.EnderecoRepository;
import com.jpdev.cursomc.repositories.EstadoRepository;
import com.jpdev.cursomc.repositories.ItemPedidoRepository;
import com.jpdev.cursomc.repositories.PagamentoRepository;
import com.jpdev.cursomc.repositories.PedidoRepository;
import com.jpdev.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Suplementos");
		Categoria cat4 = new Categoria(null, "Perfumaria");
		Categoria cat5 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat6 = new Categoria(null, "Eletronicos");
		Categoria cat7 = new Categoria(null, "Cozinha");
		Categoria cat8 = new Categoria(null, "Sala");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 900.00);
		Produto p3 = new Produto(null, "Mouse", 900.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "123456789", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("5468546", "87654236"));
		
		Endereco e1 = new Endereco(null, "Rua das Flores", "1450", "ap423", "Centro", "1965482", cli1, c3);
		Endereco e2 = new Endereco(null, "Av Brasil", "1000", "ap758", "Faria Lima", "5546982", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("20/12/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("06/01/2021 10:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/01/2021 14:56"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
