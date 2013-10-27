using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class ApplicationWarnigMap : ClassMap<ApplicationWarning>
    {
        public ApplicationWarnigMap()
        {
            Table("tbCoO_Aviso");
            Id(x => x.Id).Column("PK_int_Aviso").Not.Nullable().GeneratedBy.Identity();
            References(x => x.Application).Column("FK_int_Aplicativo").Not.Nullable();
            Map(x => x.Message).Column("vch_Mensagem").Not.Nullable();
            Map(x => x.DateTimeOfStart).Column("dtt_Inicio").Not.Nullable();
            Map(x => x.DateTimeOfEnd).Column("dtt_Final").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}


