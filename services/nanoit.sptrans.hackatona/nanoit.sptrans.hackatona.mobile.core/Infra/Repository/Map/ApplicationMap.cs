using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class ApplicationMap : ClassMap<domain.entity.Application>
    {
        public ApplicationMap()
        {
            Table("tbCoO_Aplicativo");
            Id(x => x.Id).GeneratedBy.Identity().Column("PK_int_Aplicativo").GeneratedBy.Assigned();
            References(x => x.Plataform).Column("FK_int_Plataforma").Not.Nullable();
            Map(x => x.Name).Column("vch_Aplicativo").Nullable().Length(255);
            Map(x => x.Description).Column("vch_Descricao").Nullable().Length(2147483647);
            Map(x => x.ApplicationVersion).Column("vch_VersaoAplicativo").Nullable().Length(8000);
            Map(x => x.StoreVersion).Column("vch_VersaoLoja").Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
        }
    }
}
