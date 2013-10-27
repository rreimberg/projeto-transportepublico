using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class PlataformMap : ClassMap<Plataform>
    {
        public PlataformMap()
        {
            Table("tbCoO_Plataforma");
            Id(x => x.Id).Column("PK_int_Plataforma").Not.Nullable().GeneratedBy.Identity();
            Map(x => x.Name).Column("vch_Plataforma").Not.Nullable().Length(255);
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}
