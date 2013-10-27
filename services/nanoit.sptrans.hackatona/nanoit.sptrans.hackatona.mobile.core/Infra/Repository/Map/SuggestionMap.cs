using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class SuggestionMap : ClassMap<Suggestion>
    {
        public SuggestionMap()
        {
            Table("tbCoO_Sugestao");
            Id(x => x.Id).Column("PK_int_Sugestao").Not.Nullable().GeneratedBy.Identity();
            References(x => x.Device).Column("FK_vch_DeviceId").Not.Nullable();
            References(x => x.Application).Column("FK_int_Aplicativo").Not.Nullable();
            Map(x => x.Email).Column("vch_Email").Not.Nullable();
            Map(x => x.Message).Column("vch_Mensagem").Not.Nullable();
            Map(x => x.DateTimeOfSendEmail).Column("dtt_EnvioEmail").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}
