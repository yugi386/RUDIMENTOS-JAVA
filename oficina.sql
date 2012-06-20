-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Jun 20, 2012 as 03:51 AM
-- Versão do Servidor: 5.5.8
-- Versão do PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `oficina`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(60) NOT NULL,
  `endereco` varchar(60) NOT NULL,
  `complemento` varchar(60) NOT NULL,
  `bairro` varchar(60) NOT NULL,
  `cidade` varchar(60) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `saldo_aberto` double NOT NULL,
  `ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `razao_social`, `endereco`, `complemento`, `bairro`, `cidade`, `estado`, `cep`, `tel`, `saldo_aberto`, `ativo`) VALUES
(1, 'Laticínos Queijo', 'Rua das flores, 34', 'Edifício Alto', 'Centro', 'São José dos Campos', 'SP', '12300-000', '(12)0000-0000', 0, 'S'),
(3, 'Mercearia Dimas', 'Rua Dois', 'Postos', 'centro', 'São Paulo', 'SP', '12300-000', '(12)0000-1000', 0, 'S');

-- --------------------------------------------------------

--
-- Estrutura da tabela `itens`
--

CREATE TABLE IF NOT EXISTS `itens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `venda_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `quant` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `itens`
--

INSERT INTO `itens` (`id`, `venda_id`, `produto_id`, `quant`) VALUES
(2, 1, 1, 2);

-- --------------------------------------------------------

--
-- Estrutura stand-in para visualizar `pag`
--
CREATE TABLE IF NOT EXISTS `pag` (
`venda_id` int(11)
,`cliente_id` int(11)
,`data_venda` date
,`status` varchar(1)
,`total` double
,`data_pag` date
,`valor_pago` double
);
-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

CREATE TABLE IF NOT EXISTS `pagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_pag` date NOT NULL,
  `venda_id` int(11) NOT NULL,
  `valor_pago` double NOT NULL,
  `desconto` double NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`id`, `data_pag`, `venda_id`, `valor_pago`, `desconto`, `status`) VALUES
(5, '2012-06-19', 1, 400, 0, 'P');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(1) NOT NULL,
  `descricao` varchar(60) NOT NULL,
  `saldo_atual` float NOT NULL,
  `preco` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`id`, `tipo`, `descricao`, `saldo_atual`, `preco`) VALUES
(1, 'P', 'Pneu', 8, 200),
(2, 'S', 'trocar pneu', 0, 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda`
--

CREATE TABLE IF NOT EXISTS `venda` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `data_venda` date NOT NULL,
  `status` varchar(1) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `venda`
--

INSERT INTO `venda` (`id`, `cliente_id`, `data_venda`, `status`, `total`) VALUES
(1, 1, '2003-01-01', 'V', 400);

-- --------------------------------------------------------

--
-- Estrutura para visualizar `pag`
--
DROP TABLE IF EXISTS `pag`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pag` AS select `venda`.`id` AS `venda_id`,`venda`.`cliente_id` AS `cliente_id`,`venda`.`data_venda` AS `data_venda`,`venda`.`status` AS `status`,`venda`.`total` AS `total`,`pagamento`.`data_pag` AS `data_pag`,`pagamento`.`valor_pago` AS `valor_pago` from (`pagamento` left join `venda` on((`pagamento`.`venda_id` = `venda`.`id`)));
