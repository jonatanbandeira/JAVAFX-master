select nomeCidade, 
       count(*) as quantidade,
       sx.nomeSexo
from cidade cid
inner join pets pt   on (cid.cdCidade = pt.cdcidade)
inner join sexo sx   on (sx.cdSexo = pt.cdSexo)
group by cid.nomeCidade, sx.nomeSexo
order by cid.nomeCidade, sx.nomeSexo;