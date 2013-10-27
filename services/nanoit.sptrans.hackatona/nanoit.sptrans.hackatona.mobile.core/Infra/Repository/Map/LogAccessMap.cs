using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class LogAccessMap : ClassMap<LogAccess>
    {
        public LogAccessMap()
        {
            Table("tbCoO_LogAcesso");
            Id(x => x.Id).Column("PK_int_LogAcesso").Not.Nullable().GeneratedBy.Identity();
            Map(x => x.DateTimeOfAccess).Column("dtt_Acesso").Not.Nullable();
            References(x => x.Device).Column("FK_vch_DeviceId").Not.Nullable();
            References(x => x.Application).Column("FK_int_Aplicativo").Not.Nullable();
        }
    }
}