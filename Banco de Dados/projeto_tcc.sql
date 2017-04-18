-- MySQL Script generated by MySQL Workbench
-- Sun Apr 16 20:57:09 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema projeto_tcc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projeto_tcc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projeto_tcc` DEFAULT CHARACTER SET latin1 ;
USE `projeto_tcc` ;

-- -----------------------------------------------------
-- Table `projeto_tcc`.`perfil_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`perfil_usuario` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`perfil_usuario` (
  `id` INT(11) NOT NULL,
  `tipo` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`administrador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`administrador` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`administrador` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(100) NULL DEFAULT NULL,
  `id_perfil_FK` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_perfil_FK` (`id_perfil_FK` ASC),
  CONSTRAINT `administrador_ibfk_1`
    FOREIGN KEY (`id_perfil_FK`)
    REFERENCES `projeto_tcc`.`perfil_usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`perfil` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`perfil` (
  `idperfil` INT(11) NOT NULL AUTO_INCREMENT,
  `nmperfil` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idperfil`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`usuario` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`usuario` (
  `idusuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nmusuario` VARCHAR(100) NOT NULL,
  `idperfil` INT(11) NOT NULL,
  `deSenha` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idusuario`),
  INDEX `idperfil_idx` (`idperfil` ASC),
  CONSTRAINT `idperfil`
    FOREIGN KEY (`idperfil`)
    REFERENCES `projeto_tcc`.`perfil` (`idperfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`cliente` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(50) NULL DEFAULT NULL,
  `CPF` VARCHAR(50) NULL DEFAULT NULL,
  `ENDERECO` VARCHAR(50) NULL DEFAULT NULL,
  `TELEFONE` VARCHAR(50) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(50) NULL DEFAULT NULL,
  `idusuario` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idusuario_idx` (`idusuario` ASC),
  CONSTRAINT `idusuario`
    FOREIGN KEY (`idusuario`)
    REFERENCES `projeto_tcc`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`funcionario` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`funcionario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(50) NULL DEFAULT NULL,
  `MATRICULA` VARCHAR(50) NULL DEFAULT NULL,
  `DATA_ADMISSIONAL` VARCHAR(30) NULL DEFAULT NULL,
  `LOGIN` VARCHAR(50) NULL DEFAULT NULL,
  `SENHA` VARCHAR(50) NULL DEFAULT NULL,
  `id_perfil_FK` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_perfil_FK` (`id_perfil_FK` ASC),
  CONSTRAINT `funcionario_ibfk_1`
    FOREIGN KEY (`id_perfil_FK`)
    REFERENCES `projeto_tcc`.`perfil_usuario` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`pedido` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`pedido` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idcliente` INT(11) NOT NULL,
  `valor` DECIMAL(5,2) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  `dtpedido` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `pedido_cliente_idx` (`idcliente` ASC),
  CONSTRAINT `pedido_cliente`
    FOREIGN KEY (`idcliente`)
    REFERENCES `projeto_tcc`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`produto` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`produto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `valor` DECIMAL(5,2) NOT NULL,
  `nomeimagem` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `projeto_tcc`.`item_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto_tcc`.`item_pedido` ;

CREATE TABLE IF NOT EXISTS `projeto_tcc`.`item_pedido` (
  `iditem` INT(11) NOT NULL AUTO_INCREMENT,
  `idpedido` INT(11) NOT NULL,
  `idproduto` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  PRIMARY KEY (`iditem`),
  INDEX `item_pedido_idx` (`idpedido` ASC),
  INDEX `item_cliente_idx` (`idproduto` ASC),
  CONSTRAINT `item_pedido`
    FOREIGN KEY (`idpedido`)
    REFERENCES `projeto_tcc`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_produto`
    FOREIGN KEY (`idproduto`)
    REFERENCES `projeto_tcc`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
