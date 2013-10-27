using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class LineRouteMap : ClassMap<LineRoute>
    {
        public LineRouteMap()
        {
            Table("tbCoO_LinhaRota");
            Id(x => x.Id).Column("PK_int_LinhaRota").GeneratedBy.Assigned();
            References(x => x.Line).Column("FK_int_Linha").Not.Nullable();
            Map(x => x.Latitude).Column("vch_Latitude").Nullable();
            Map(x => x.Longitude).Column("vch_Longitude").Nullable();
            Map(x => x.Sequence).Column("int_Sequencia").Nullable();
        }
    }
}
