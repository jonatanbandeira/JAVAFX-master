SELECT produtos.*, categorias.descricao FROM produtos, categorias
WHERE produtos.cdcategoria = categorias.cdcategoria
ORDER BY produtos.cdproduto

SELECT produtos.*, categorias.descricao FROM produtos
JOIN categoias ON produtos.cdcategoria = categorias.cdcategoria
ORDER BY produtos.cdproduto