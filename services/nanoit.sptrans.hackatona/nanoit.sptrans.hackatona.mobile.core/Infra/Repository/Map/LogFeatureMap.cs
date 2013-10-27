using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class LogFeatureMap : ClassMap<LogFeature>
    {
        public LogFeatureMap()
        {
            Table("tbCoO_LogFuncionalidade");
            Id(x => x.Id).Column("PK_int_LogFuncionalidade").Not.Nullable().GeneratedBy.Identity();
            References(x => x.Feature).Column("FK_int_Funcionalidade").Not.Nullable();
            References(x => x.Device).Column("FK_vch_DeviceId").Not.Nullable();
            Map(x => x.DateTimeOfAccess).Column("dtt_Acesso").Not.Nullable();
        }
    }
}


