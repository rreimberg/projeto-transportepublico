using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class BusTypeSuggestionMap : ClassMap<BusTypeSuggestion>
    {
        public BusTypeSuggestionMap()
        {
            Table("tbCoO_TipoOnibusSugestao");
            Id(x => x.Id).Column("PK_int_TipoOnibusSugestap").Not.Nullable().GeneratedBy.Identity();
            References(x => x.BusType).Column("FK_int_TipoOnibus").Not.Nullable();
            References(x => x.Bus).Column("FK_int_SPTransId").Not.Nullable();
            References(x => x.Device).Column("FK_vch_DeviceId").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}


