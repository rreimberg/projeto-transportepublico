using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class BusTypeMap : ClassMap<BusType>
    {
        public BusTypeMap()
        {
            Table("tbCoO_TipoOnibus");
            Id(x => x.Id).Column("PK_int_TipoOnibus").Not.Nullable().GeneratedBy.Identity();
            Map(x => x.Name).Column("vch_TipoOnibus").Not.Nullable();
            Map(x => x.IsActive).Column("bit_Ativo").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}


