-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 24-Nov-2019 às 00:25
-- Versão do servidor: 10.3.16-MariaDB
-- versão do PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `jogodaforcakcs`
--

-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS `jogodaforca`;

use `jogodaforca`;

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `descricao` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`) VALUES
(1, 'Fruta'),
(2, 'Animal'),
(3, 'Objeto'),
(4, 'Profissão');

-- --------------------------------------------------------

--
-- Estrutura da tabela `dificuldade`
--

CREATE TABLE `dificuldade` (
  `id` int(11) NOT NULL,
  `descricao` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `dificuldade`
--

INSERT INTO `dificuldade` (`id`, `descricao`) VALUES
(1, 'Fácil'),
(2, 'Normal'),
(3, 'Difícil');

-- --------------------------------------------------------

--
-- Estrutura da tabela `palavra`
--

CREATE TABLE `palavra` (
  `id` int(11) NOT NULL,
  `descricao` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `dificuldade_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `palavra`
--

INSERT INTO `palavra` (`id`, `descricao`, `dificuldade_id`) VALUES
(1, 'abacaxi', 3),
(2, 'acerola', 3),
(3, 'banana', 3),
(4, 'caju', 2),
(5, 'cereja', 2),
(6, 'limão', 2),
(7, 'manga', 1),
(8, 'pêra', 1),
(9, 'uva', 1),
(10, 'abelha', 3),
(11, 'anta', 2),
(12, 'baleia', 3),
(13, 'burro', 1),
(14, 'cobra', 2),
(15, 'cachorro', 3),
(16, 'elefante', 2),
(17, 'foca', 1),
(18, 'galo', 1),
(19, 'telefone', 3),
(20, 'sofá', 1),
(21, 'pipa', 1),
(22, 'panela', 2),
(23, 'faca', 1),
(24, 'agulha', 2),
(25, 'banco', 2),
(26, 'cadeira', 1),
(27, 'dado', 1),
(28, 'advogado', 2),
(29, 'barbeiro', 2),
(30, 'cantor', 1),
(31, 'dentista', 2),
(32, 'enfermeira', 3),
(33, 'lixeiro', 3),
(34, 'militar', 3),
(35, 'padeiro', 1),
(36, 'segurança', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `palavra_categoria`
--

CREATE TABLE `palavra_categoria` (
  `palavra_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Extraindo dados da tabela `palavra_categoria`
--

INSERT INTO `palavra_categoria` (`palavra_id`, `categoria_id`) VALUES
(1, 1),
(10, 2),
(2, 1),
(28, 4),
(24, 3),
(11, 2),
(12, 2),
(3, 1),
(25, 3),
(29, 4),
(13, 2),
(15, 2),
(26, 3),
(4, 1),
(30, 4),
(5, 1),
(14, 2),
(27, 3),
(31, 4),
(16, 2),
(32, 4),
(23, 3),
(17, 2),
(18, 2),
(6, 1),
(33, 4),
(7, 1),
(34, 4),
(35, 4),
(22, 3),
(21, 3),
(8, 1),
(36, 4),
(20, 3),
(19, 3),
(9, 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `dificuldade`
--
ALTER TABLE `dificuldade`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `palavra`
--
ALTER TABLE `palavra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dificuldade_id` (`dificuldade_id`) USING BTREE;

--
-- Índices para tabela `palavra_categoria`
--
ALTER TABLE `palavra_categoria`
  ADD KEY `categoria_id` (`categoria_id`),
  ADD KEY `palavra_id` (`palavra_id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `dificuldade`
--
ALTER TABLE `dificuldade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `palavra`
--
ALTER TABLE `palavra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `palavra`
--
ALTER TABLE `palavra`
  ADD CONSTRAINT `fk_dificuldade` FOREIGN KEY (`dificuldade_id`) REFERENCES `dificuldade` (`id`);

--
-- Limitadores para a tabela `palavra_categoria`
--
ALTER TABLE `palavra_categoria`
  ADD CONSTRAINT `categoria_id` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `palavra_id` FOREIGN KEY (`palavra_id`) REFERENCES `palavra` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
