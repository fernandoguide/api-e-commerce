package br.com.ecommerce.api.services;

import br.com.ecommerce.api.domain.*;
import br.com.ecommerce.api.repositories.*;
import br.com.ecommerce.api.domain.enums.EstadoPagamento;
import br.com.ecommerce.api.domain.enums.Perfil;
import br.com.ecommerce.api.domain.enums.TipoCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Service
public class DBService {

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
    @Autowired
    private BCryptPasswordEncoder pe;

    public void instantiateTestDatabase() throws ParseException {

        Categoria cat1 = new Categoria(null, "Hardware");
        Categoria cat2 = new Categoria(null, "Acessorios");
        Categoria cat3 = new Categoria(null, "Perifericos");
        Categoria cat4 = new Categoria(null, "Monitores");
        Categoria cat5 = new Categoria(null, "Computador");
        Categoria cat6 = new Categoria(null, "Notebooks");
        Categoria cat7 = new Categoria(null, "SmartFones");
        Categoria cat8 = new Categoria(null, "All");
//		Hardware
        Produto p1 = new Produto(null, "Memória HyperX Fury, 8GB, 2666MHz, DDR4, CL16, Preto - HX426C16FB3/8", 239.9);
        Produto p2 = new Produto(null, "Memória Corsair Vengeance LPX, 8GB, 2400MHz, DDR4, CL16, Preto - CMK8GX4M1A2400C16", 239.9);
        Produto p3 = new Produto(null, "Memória Adata XPG Spectrix D41 TUF, RGB, 8GB, 3000MHz, DDR4, CL16 - AX4U300038G16-SB41", 309.9);
        Produto p4 = new Produto(null, "Placa de Som HyperX Amp USB Virtual 7.1 Surround Sound - HX-USCCAMSS-BK", 99.9);
        Produto p5 = new Produto(null, "Placa de Som HyperX Dolby 7.1, Linha Revolver S - HXS-HSDG3", 109.9);
        Produto p6 = new Produto(null, "Placa de Som Asus Xonar SE, PCIe, Canal 5.1 - 90YA00T0-M0UA00", 229.9);
        Produto p7 = new Produto(null, "HD Seagate BarraCuda, 4TB, 3.5´, SATA - ST4000DM004", 579.9);
        Produto p8 = new Produto(null, "HD Seagate BarraCuda, 2TB, 3.5´, SATA - ST2000DM008", 328.9);
        Produto p9 = new Produto(null, "Case C3 Tech p/ HD 2.5´ USB 3.0, Preto - CH-300BK", 37.9);
        Produto p10 = new Produto(null, "HD Seagate BarraCuda, 1TB, 3.5´, SATA - ST1000DM010", 236.9);
        Produto p11 = new Produto(null, "SSD HP S700, 250GB, SATA, Leituras: 555Mb/s e Gravações: 515Mb/s - 2DP98AA#ABL", 309.9);
        Produto p12 = new Produto(null, "SSD Kingston A400, 120GB, SATA, Leitura 500MB/s, Gravação 320MB/s - SA400S37/120G", 170.90);
        Produto p13 = new Produto(null, "SSD Kingston A400, 480GB, SATA, Leitura 500MB/s, Gravação 450MB/s - SA400S37/480G", 389.9);
        Produto p14 = new Produto(null, "SSD Crucial BX500, 240GB, SATA, Leitura 540MB/s, Gravação 500MB/s - CT240BX500SSD1", 239.9);
//		Acessorios
        Produto p15 = new Produto(null, "Pen Drive Multilaser Twist USB 2.0 8GB PD587", 18.9);
        Produto p16 = new Produto(null, "Pen Drive Kingston DataTraveler USB 3.0 32GB - DT100G3/32GB", 28.9);
//		Perifericos
        Produto p17 = new Produto(null, "Headset Gamer HyperX Cloud Stinger - HX-HSCS-BK/NA", 329.9);
        Produto p18 = new Produto(null, "Headset Gamer Razer Kraken Essential com Microfone - P2 - RZ04-01720100-R3R1", 299.9);
        Produto p19 = new Produto(null, "Fone de Ouvido Intra-Auricular Gamer HyperX Cloud Earbuds, Vermelho - HX-HSCEB-RD", 184.9);
        Produto p20 = new Produto(null, "Fone de Ouvido Bluetooth 4.0 Intra Auricular JBL Preto - T110BT - 28910704", 99.9);
        Produto p21 = new Produto(null, "Teclado Mecânico Gamer Redragon Kumara, LED Vermelho, Switch Outemu Blue, PT - K552", 188.9);
        Produto p22 = new Produto(null, "Teclado Logitech K120, ABNT2", 54.9);
        Produto p23 = new Produto(null, "Mouse Logitech M90 Preto 1000DPI", 24.9);
        Produto p24 = new Produto(null, "Mouse Gamer Redragon 10000DPI Chroma Cobra M711", 99.9);
//		Monitores
        Produto p25 = new Produto(null, "Monitor Gamer Asus LED 24´ Widescreen, Full HD, HDMI/VGA/DVI, 1ms - VS248HR", 679.9);
        Produto p26 = new Produto(null, "Monitor Philips LED LCD 18.5 , HDMI, VGA - 193V5LHSB2", 319.9);
        Produto p27 = new Produto(null, "Monitor Gamer AOC Hero LED 24´ Widescreen, Full HD, HDMI/VGA/DVI/Display Port, FreeSync, Som Integrado, 144Hz, 1ms, Altura Ajustável - G2460PF", 1199.9);
        Produto p28 = new Produto(null, "Monitor LG LED 29´ Ultrawide, IPS, HDMI, FreeSync - 29WK500", 999.9);
        Produto p29 = new Produto(null, "Monitor LG LED 19.5´ Widescreen, VGA - 20M37AA", 329.9);
//		Computador
        Produto p30 = new Produto(null, "Computador Gamer G-Fire AMD Ryzen 5 2400G, 8GB, HD 1TB, Radeon RX Vega 11 (integrada), Linux - HTG -R253", 2221.9);
        Produto p31 = new Produto(null, "Computador Positivo Master D2200, Intel Core i3-8100, 4GB, HD 1TB, Shell EFI - 1305580", 1499.9);
        Produto p32 = new Produto(null, "Computador NTC Price 4052 AR, Intel Core i3-4170, 4GB, SSD 120GB, Linux - 15538", 1299.9);
        Produto p33 = new Produto(null, "Computador Lenovo V530s, Intel Core i3-8100, 4GB, 500GB, Windows 10 Pro - 10TXA01FBP", 2299.9);
        Produto p34 = new Produto(null, "Computador HP 285 Pro A MT, AMD A6-9500, 4GB, SSD 128GB, FreeDOS 2.0 - 5CL91LA", 1468.6);
//		NoteBook
        Produto p35 = new Produto(null, "Notebook Positivo Motion Intel Core i3-7020U, 4GB, 1TB, Windows 10 Home, 14´, Cinza - 3011572", 1599.9);
        Produto p36 = new Produto(null, "Notebook Lenovo Ultrafino Ideapad S145, AMD Ryzen 7-3700U, 4GB, SSD 256GB, Windows 10, 15.6´, Prata - 81V70000BR", 2999.9);
        Produto p37 = new Produto(null, "Notebook Asus VivoBook 15, Intel Core i5-7200U, 4GB, 1TB, 15.6´, Windows 10 Home, Cinza - X510UA-BR665T", 2374.9);
        Produto p38 = new Produto(null, "Notebook HP 246 G6, Intel i5-7200U, 4GB, 1TB, Windows 10 Home, 14´ - 7KV39LA#AC4", 2349.9);
        Produto p39 = new Produto(null, "Notebook Gamer Acer Aspire Nitro 5, Intel Core i7-8750H, 16GB, 1TB, SSD 128GB, GTX 1050Ti 4GB, W10 Home, 15.6´ - AN515-52-72UU", 5599.9);
//		SmartFones
        Produto p40 = new Produto(null, "Smartphone Samsung Galaxy A10, 32GB, 13MP, Tela 6.2´, Preto - SM-A105M/32DL", 639.90);
        Produto p41 = new Produto(null, "iPhone 11 Preto, 128GB - MWM02", 5299.00);
        Produto p42 = new Produto(null, "iPhone 8 Plus Cinza Espacial, 64GB - MQ8L2", 3903.34);
        Produto p43 = new Produto(null, "Smartphone Asus ROG Phone II, 128GB, 48MP, Tela 6.59´, Preto - ZS660KL-1A038BR", 4273.90);
        Produto p44 = new Produto(null, "Smartphone Asus Zenfone 6, 64GB, 48MP, Tela 6.4´, Preto - ZS630KL-2A035WW ", 2699.00);
        Produto p45 = new Produto(null, "Smartphone Motorola Moto G8 Play, 32GB, 13MP, Tela 6.2´, Preto Ônix - XT2015-2 ", 939.64);
        Produto p46 = new Produto(null, "Smartphone Multilaser MS60X Plus, 16GB, 13MP, Tela 5.7´, Dourado/Branco + Capa e Película - P9084", 524.90);
        Produto p47 = new Produto(null, "Smartphone Samsung Galaxy J7 Prime 2, 32GB, 13MP, Tela 5.5´, TV Digital, Preto - SM-G611M ", 958.90);
        Produto p48 = new Produto(null, "Smartphone Samsung Galaxy J6, 32GB, 13MP, Tela 5.6´, TV Digital, Preto - SM-J600GT ", 679.90);
        Produto p49 = new Produto(null, "Smartphone Samsung Galaxy S10, 128GB, 16MP, Tela 6.1´, Azul - SM-G973F/1DL ", 3270.90);
        Produto p50 = new Produto(null, "Smartphone LG G8S ThinQ, 128GB, 13MP, Tela 6.21´, Preto - LM-G810EAW ", 2999.90);

//		Hardware
        p1.getCategorias().add(cat1);
        p2.getCategorias().add(cat1);
        p3.getCategorias().add(cat1);
        p4.getCategorias().add(cat1);
        p5.getCategorias().add(cat1);
        p6.getCategorias().add(cat1);
        p7.getCategorias().add(cat1);
        p8.getCategorias().add(cat1);
        p9.getCategorias().add(cat1);
        p10.getCategorias().add(cat1);
        p11.getCategorias().add(cat1);
        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
//		Acessorios
        p15.getCategorias().add(cat2);
        p16.getCategorias().add(cat2);
//		Perifericos
        p17.getCategorias().add(cat3);
        p18.getCategorias().add(cat3);
        p19.getCategorias().add(cat3);
        p20.getCategorias().add(cat3);
        p21.getCategorias().add(cat3);
        p22.getCategorias().add(cat3);
        p23.getCategorias().add(cat3);
        p24.getCategorias().add(cat3);
//		Monitores
        p25.getCategorias().add(cat4);
        p26.getCategorias().add(cat4);
        p27.getCategorias().add(cat4);
        p28.getCategorias().add(cat4);
        p29.getCategorias().add(cat4);
//		Computadores
        p30.getCategorias().add(cat5);
        p31.getCategorias().add(cat5);
        p32.getCategorias().add(cat5);
        p33.getCategorias().add(cat5);
        p34.getCategorias().add(cat5);
//		NoteBooks
        p35.getCategorias().add(cat6);
        p36.getCategorias().add(cat6);
        p37.getCategorias().add(cat6);
        p38.getCategorias().add(cat6);
        p39.getCategorias().add(cat6);
//		SmartFones
        p40.getCategorias().add(cat7);
        p41.getCategorias().add(cat7);
        p42.getCategorias().add(cat7);
        p43.getCategorias().add(cat7);
        p44.getCategorias().add(cat7);
        p45.getCategorias().add(cat7);
        p46.getCategorias().add(cat7);
        p47.getCategorias().add(cat7);
        p48.getCategorias().add(cat7);
        p49.getCategorias().add(cat7);
        p50.getCategorias().add(cat7);
//        ALL
        p3.getCategorias().add(cat8);
        p7.getCategorias().add(cat8);
        p15.getCategorias().add(cat8);
        p23.getCategorias().add(cat8);
        p28.getCategorias().add(cat8);
        p30.getCategorias().add(cat8);
        p37.getCategorias().add(cat8);
        p41.getCategorias().add(cat8);
        p48.getCategorias().add(cat8);
        p50.getCategorias().add(cat8);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14));
        cat2.getProdutos().addAll(Arrays.asList(p15, p16));
        cat3.getProdutos().addAll(Arrays.asList(p17, p18, p19, p20, p21, p22, p23, p24));
        cat4.getProdutos().addAll(Arrays.asList(p25, p26, p27, p28, p29));
        cat5.getProdutos().addAll(Arrays.asList(p30, p31, p32, p33, p34));
        cat6.getProdutos().addAll(Arrays.asList(p35, p36, p37, p38, p39));
        cat7.getProdutos().addAll(Arrays.asList(p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        cat8.getProdutos().addAll(Arrays.asList(p3, p7, p15, p23, p28, p30, p37, p41, p48, p50));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7,cat8));

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25,
                p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46,
                p47, p48, p49, p50));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");
        Estado est3 = new Estado(null, "Rio de Janeiro");
        Estado est4 = new Estado(null, "Rio Grande do Sul");
        Estado est5 = new Estado(null, "Paraná");
        Estado est6 = new Estado(null, "Santa Catarina");
        Estado est7 = new Estado(null, "Bahia");
        Estado est8 = new Estado(null, "Espirito Santo");
        Estado est9 = new Estado(null, "Goias");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);
        Cidade c4 = new Cidade(null, "Uberaba", est1);
        Cidade c5 = new Cidade(null, "Belo Horizonte", est1);
        Cidade c6 = new Cidade(null, "Sete Lagoas", est1);
        Cidade c7 = new Cidade(null, "Santos", est2);
        Cidade c8 = new Cidade(null, "Guaruja", est2);
        Cidade c9 = new Cidade(null, "Guaratingueta", est2);
        Cidade c10 = new Cidade(null, "Ribeirao Preto", est2);
        Cidade c11 = new Cidade(null, "Bauru", est2);
        Cidade c12 = new Cidade(null, "Rio de Janeiro", est3);
        Cidade c13 = new Cidade(null, "Niteroi", est3);
        Cidade c14 = new Cidade(null, "Porto Alegre", est4);
        Cidade c15 = new Cidade(null, "Curitiba", est5);
        Cidade c16 = new Cidade(null, "Florianopolis", est6);
        Cidade c17 = new Cidade(null, "Salvador", est7);
        Cidade c18 = new Cidade(null, "Vitoria", est8);
        Cidade c19 = new Cidade(null, "Goiania", est9);
        Cidade c20 = new Cidade(null, "Resende", est3);


        est1.getCidades().addAll(Arrays.asList(c1, c4, c5, c6));
        est2.getCidades().addAll(Arrays.asList(c2, c3, c7, c8, c9, c10, c11));
        est3.getCidades().addAll(Arrays.asList(c20, c12, c13));
        est4.getCidades().addAll(Arrays.asList(c14));
        est5.getCidades().addAll(Arrays.asList(c15));
        est6.getCidades().addAll(Arrays.asList(c16));
        est7.getCidades().addAll(Arrays.asList(c17));
        est8.getCidades().addAll(Arrays.asList(c18));
        est9.getCidades().addAll(Arrays.asList(c19));


        estadoRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5, est6, est7, est8, est9));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20));

        Cliente cli1 = new Cliente(null, "Administrador", "eletronicstore2020@gmail.com",
                "31628382740", TipoCliente.PESSOAFISICA, pe.encode("admin"));

        Cliente cli2 = new Cliente(null, "Fernando Oliveira", "fernandoguide2014@gmail.com",
                "31628382740", TipoCliente.PESSOAFISICA, pe.encode("admin"));

        Cliente cli3 = new Cliente(null, "usuario", "usuario@gmail.com",
                "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123"));

        Cliente cli4 = new Cliente(null, "Eletronic Store", "eletronicstore2020@outlook.com",
                "18221300000166", TipoCliente.PESSOAJURIDICA, pe.encode("admin"));


        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
        cli1.addPerfil(Perfil.ADMIN);

        cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
        cli2.addPerfil(Perfil.ADMIN);

        cli3.getTelefones().addAll(Arrays.asList("93883321", "34252625"));

        cli4.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
        cli4.addPerfil(Perfil.ADMIN);

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
        Endereco e3 = new Endereco(null, "Rua Jose Floriano", "100", null, "Jd Guarani", "281777012", cli2, c2);
        Endereco e4 = new Endereco(null, "Rua do meio ", "12", null, "Centro", "281777012", cli3, c4);
        Endereco e5 = new Endereco(null, "Rua do fundo ", "132", null, "Centro", "281777012", cli2, c5);
        Endereco e6 = new Endereco(null, "rua dois ", "500", null, "Centro", "281777012", cli1, c6);
        Endereco e7 = new Endereco(null, "Avenida Joaquim Floriano", "333", null, "Vila Yara", "281777012", cli1, c7);
        Endereco e8 = new Endereco(null, "Avenida Jose da Silva", "44", null, "Centro", "281777012", cli3, c8);
        Endereco e9 = new Endereco(null, "Avenida Abacate", "20", null, "Vila Velha", "281777012", cli2, c9);
        Endereco e10 = new Endereco(null, "Avenida Flores de Matos", "777", null, "Centro", "281777012", cli1, c10);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2, e3, e6, e7, e10));
        cli2.getEnderecos().addAll(Arrays.asList(e3, e2, e5, e9));
        cli3.getEnderecos().addAll(Arrays.asList(e1, e2, e3, e4, e8));
        cli4.getEnderecos().addAll(Arrays.asList(e1, e2, e3, e4, e8));


        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
        enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, new Date(System.currentTimeMillis()), cli1, e10);
        Pedido ped2 = new Pedido(null, new Date(System.currentTimeMillis()), cli1, e2);


        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2020 00:00"),
                null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 239.9);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 309.9);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 239.9);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Collections.singletonList(ip3));

        p1.getItens().addAll(Collections.singletonList(ip1));
        p2.getItens().addAll(Collections.singletonList(ip3));
        p3.getItens().addAll(Collections.singletonList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
