using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class ErrorMap : ClassMap<Error>
    {
        public ErrorMap()
        {
            Table("tbCoO_Erro");
            Id(x => x.Id).Column("PK_int_Erro").Not.Nullable().GeneratedBy.Identity();
            Map(x => x.ErrorMessage).Column("vch_Erro").Nullable();
            Map(x => x.DateTimeOfError).Column("dtt_Erro").Not.Nullable();
            Map(x => x.Device).Column("vch_DeviceId").Nullable();
            Map(x => x.OperationSystem).Column("vch_SistemaOperacional").Nullable();
        }
    }
}