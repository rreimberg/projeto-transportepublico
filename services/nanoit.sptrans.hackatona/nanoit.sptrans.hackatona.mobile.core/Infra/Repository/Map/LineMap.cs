using FluentNHibernate.Mapping;
using nanoit.sptrans.hackatona.core.domain.entity;

namespace nanoit.sptrans.hackatona.core.Infra.Repository.Map
{
    public sealed class LineMap : ClassMap<Line>
    {
        public LineMap()
        {
            Table("tbCoO_Linha");
            Id(x => x.Id).Column("PK_int_Linha").GeneratedBy.Assigned();
            Map(x => x.SPTransId).Column("vch_SPTransId").Nullable();
            Map(x => x.OlhoVivoId).Column("vch_OlhoVivoId").Nullable();
            Map(x => x.PrefixLabel).Column("vch_Prefixo").Nullable();
            Map(x => x.PrefixId).Column("int_PrefixoTipo").Nullable();
            Map(x => x.LabelSourceName).Column("vch_NomeLetreiroOrigem").Not.Nullable();
            Map(x => x.LabelTargetName).Column("vch_NomeLetreiroDestino").Not.Nullable();
            Map(x => x.Direction).Column("int_Sentido").Not.Nullable();
            Map(x => x.DateTimeOfCreation).Column("dtt_Inclusao").Not.Nullable();
            Map(x => x.DateTimeOfLastUpdate).Column("dtt_UltimaModificacao").Not.Nullable();
            Map(x => x.Company).Column("int_empresa").Not.Nullable();

            Map(x => x.PrefixFull).Formula("vch_Prefixo+'-'+CONVERT(VARCHAR(2),int_PrefixoTipo)");
            Map(x => x.Target).Formula("CASE WHEN INT_SENTIDO = 1 THEN vch_NomeLetreiroDestino ELSE vch_NomeLetreiroOrigem END");

            HasMany(x => x.Itineraries).KeyColumn("FK_int_Linha").Cascade.All().Not.LazyLoad();
            HasMany(x => x.Coordenates).KeyColumn("FK_int_Linha").Cascade.All().Not.LazyLoad();
        }
    }
}


