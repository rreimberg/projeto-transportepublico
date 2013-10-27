using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class LineDetailMap : ClassMap<LineDetail>
    {
        public LineDetailMap()
        {
            Table("tbCoO_LinhaDetalhe");
            Id(x => x.Id).Column("PK_int_LinhaDetalhe").Not.Nullable();
            References(x => x.Line).Column("FK_int_Linha").Not.Nullable();
            Map(x => x.Extension).Column("vch_Extensao").Nullable();
            Map(x => x.MediumInterval).Column("vch_IntervaloMedio").Nullable();
            Map(x => x.BusinessDayOperation).Column("vch_OperacaoDiaUtil").Nullable();
            Map(x => x.SaturdayOperation).Column("vch_OperacaoSabado").Nullable();
            Map(x => x.SundayOperation).Column("vch_OperacaoDomingo").Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
            Map(x => x.DateTimeOfLastUpdate).Column("dtt_UltimaModificacao").Not.Nullable();
        }
    }
}


