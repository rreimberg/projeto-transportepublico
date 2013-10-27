using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class BusMap : ClassMap<Bus>
    {
        public BusMap()
        {
            Table("tbCoO_Onibus");
            Id(x => x.Id).Column("PK_int_SPTransId").Not.Nullable().GeneratedBy.Assigned();
            References(x => x.BusType).Column("FK_int_TipoOnibus").Not.Nullable().Not.LazyLoad();
            Map(x => x.PrefixFull).Column("FK_vch_LinhaId").Not.Nullable();
            Map(x => x.AcceptSpecialPerson).Column("bit_AtendeEspecial").Not.Nullable();
            Map(x => x.DateTimeOfLastUpdate).Column("dtt_UltimaModificacao").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}