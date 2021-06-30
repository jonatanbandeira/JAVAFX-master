-- QUERY PARA O GRÁFICO

SELECT nomeRaca AS RAÇA,
	      count(*) as QUANTIDADE
FROM raca r,
	   pets p 
WHERE r.cdRaca = p.cdRaca
group by nomeRaca;