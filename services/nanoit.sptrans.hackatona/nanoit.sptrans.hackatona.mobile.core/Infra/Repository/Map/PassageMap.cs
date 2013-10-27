using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class PassageMap : ClassMap<Passage>
    {
        public PassageMap()
        {
            Table("tbCoO_Corredor");
            Id(x => x.Id).Column("PK_int_SPTransId").Not.Nullable();
            Map(x => x.Name).Column("vch_Corredor").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}