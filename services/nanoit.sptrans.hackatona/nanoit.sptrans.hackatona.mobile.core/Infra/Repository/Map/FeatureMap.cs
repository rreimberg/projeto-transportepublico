using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class FeatureMap : ClassMap<Feature>
    {
        public FeatureMap()
        {
            Table("tbCoO_Funcionalidade");
            Id(x => x.Id).Column("PK_int_Funcionalidade").Not.Nullable().GeneratedBy.Identity();
            References(x => x.Application).Column("FK_int_Aplicativo").Not.Nullable();
            Map(x => x.Name).Column("vch_Funcionalidade").Nullable();
            Map(x => x.DateTimeOfAccess).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}


