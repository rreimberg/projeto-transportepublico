using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class LogSearchMap : ClassMap<LogSearch>
    {
        public LogSearchMap()
        {
            Table("tbCoO_LogPesquisa");
            Id(x => x.Id).Column("PK_int_LogPesquisa").Not.Nullable().GeneratedBy.Identity();
            References(x => x.Device).Column("FK_vch_DeviceId").Not.Nullable();
            References(x => x.Application).Column("FK_int_Aplicativo").Not.Nullable();
            Map(x => x.Text).Column("vch_Texto").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}