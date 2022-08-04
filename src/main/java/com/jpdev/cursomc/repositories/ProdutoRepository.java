package com.jpdev.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpdev.cursomc.domain.Categoria;
import com.jpdev.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Transactional(readOnly= true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,  List<Categoria> cateogrias, PageRequest pageRequest);

}
