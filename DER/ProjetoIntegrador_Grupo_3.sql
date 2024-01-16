-- MySQL Script generated by MySQL Workbench
-- Tue Jan  9 15:04:37 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Ecommerce
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Ecommerce
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Ecommerce` DEFAULT CHARACTER SET utf8 ;
USE `Ecommerce` ;

-- -----------------------------------------------------
-- Table `Ecommerce`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ecommerce`.`Usuarios` (
  `idUsuarios` BIGINT NOT NULL AUTO_INCREMENT,
  `foto` VARCHAR(255) NULL,
  `nome` VARCHAR(80) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ecommerce`.`Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ecommerce`.`Categorias` (
  `idCategorias` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategorias`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ecommerce`.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Ecommerce`.`Produtos` (
  `idProdutos` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `descricao` VARCHAR(80) NOT NULL,
  `quantidade` BIGINT NOT NULL,
  `preco` DECIMAL(8,2) NOT NULL,
  `Categorias_idCategorias` BIGINT NOT NULL,
  `Usuarios_idUsuarios` BIGINT NOT NULL,
  PRIMARY KEY (`idProdutos`),
  INDEX `fk_Produtos_Categorias_idx` (`Categorias_idCategorias` ASC) VISIBLE,
  INDEX `fk_Produtos_Usuarios1_idx` (`Usuarios_idUsuarios` ASC) VISIBLE,
  CONSTRAINT `fk_Produtos_Categorias`
    FOREIGN KEY (`Categorias_idCategorias`)
    REFERENCES `Ecommerce`.`Categorias` (`idCategorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produtos_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuarios`)
    REFERENCES `Ecommerce`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;