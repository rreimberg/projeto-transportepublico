using System;
using System.Collections.Generic;
using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class ItineraryMap : ClassMap<Itinerary>
    {
        public ItineraryMap()
        {
            Table("tbCoO_Itinerario");
            Id(x => x.Id).Column("PK_int_Itinerario").GeneratedBy.Assigned();
            References(x => x.Line).Column("FK_int_Linha").Not.Nullable();
            Map(x => x.Sequence).Column("int_Sequencia").Not.Nullable();
            Map(x => x.Adress).Column("vch_Endereco").Nullable();
            Map(x => x.StartNumber).Column("vch_NumeroInicio").Nullable();
            Map(x => x.EndNumber).Column("vch_NumeroFinal").Nullable();
            Map(x => x.NumberFull).Formula("vch_NumeroInicio+' - '+vch_NumeroFinal");
        }
    }
}